package com.wisecrm.filestorage.service.impl;
import com.wisecrm.filestorage.dto.*;
import com.wisecrm.filestorage.dto.MinioProperties;
import com.wisecrm.filestorage.entity.*;
import com.wisecrm.filestorage.exceptions.*;
import com.wisecrm.filestorage.repository.*;
import com.wisecrm.filestorage.service.interfaces.*;
import com.wisecrm.filestorage.util.*;
import io.minio.*;
import io.minio.errors.*;
import org.springframework.stereotype.*;
import org.springframework.web.multipart.*;

import java.io.*;
import java.security.*;
import java.time.*;
import java.util.*;

@Service
public class DocumentServiceImpl implements IDocumentControl {
    private final MinioClient minioClient;

    private final MinioProperties minioProperties;

  //  private final DocumentTypeRepository documentTypeRepository;


    private final DocumentsRepository documentsRepository;

    private final ExctensionRepository exctensionRepository;

    public DocumentServiceImpl(MinioClient minioClient, MinioProperties minioProperties, DocumentsRepository documentsRepository, ExctensionRepository exctensionRepository) {
        this.minioClient = minioClient;
        this.minioProperties = minioProperties;
       // this.documentTypeRepository = documentTypeRepository;
        this.documentsRepository = documentsRepository;
        this.exctensionRepository = exctensionRepository;
    }


    @Override
    public List<DocumentDto> upload(CreateDocRequest createDocRequest) throws DocumentUploadException, UnCorrectFileNameException, NotSupportedExtension {

        List<Document> list = new ArrayList<>();

        for (MultipartFile file : createDocRequest.getFile()) {

            if (file.isEmpty() || file.getOriginalFilename() == null) {
                throw new UnCorrectFileNameException("Document must have name.");
            }

            InputStream inputStream;
            try {
                inputStream = file.getInputStream();
            } catch (Exception e) {
                throw new DocumentUploadException("Document upload failed: " + e.getMessage());
            }

            String path = saveDocument(inputStream, createDocRequest.getCompanyId(),createDocRequest.getCategory(), file);

            Document documentEntity = Document.builder()
                    .name(FileProcessor.getFileName(file.getOriginalFilename(), "."))
                    .path(path)
                    .size(FileProcessor.calcToMB(file.getSize()))
                    .loadAt(LocalDate.now())
                    .department_id(createDocRequest.getCompanyId())
                   // .type(documentTypeRepository.findById(createDocRequest.getType()).orElse(documentTypeRepository.findByFirst()))
                    .extension(exctensionRepository.findByName(FileProcessor.fileExtension(file.getOriginalFilename())).orElseThrow(NotSupportedExtension::new))
                    .build();
            documentEntity.setId(documentsRepository.save(documentEntity).getId());
            list.add(documentEntity);

        }

            return mapFromToDto(list);
    }

    private List<DocumentDto> mapFromToDto(List<Document> list) {
        List<DocumentDto> documentDtos = new ArrayList<>();

        list.forEach(item->{


            documentDtos.add(new DocumentDto(
                    item.getId(),
                    item.getName(),
                    item.getLoadAt(),
                    item.getSize(),
                    item.getDepartment_id(),
                    item.getExtension().getName()
            ));

        });

        return documentDtos;


    }

    public String saveDocument(InputStream inputStream, Long companyId, String folderName, MultipartFile file) throws DocumentUploadException {

        List<String> props = Arrays.asList(String.valueOf(companyId),
                folderName,
                file.getOriginalFilename()
        );
        String path = FileProcessor.createPath(props, "/");
        try {
            minioClient.putObject(PutObjectArgs.builder()
                    .stream(inputStream, inputStream.available(), -1)
                    .bucket(minioProperties.getDocumentsBucket())
                    .object(path)
                    .build());
        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidKeyException |
                 IOException | InvalidResponseException | NoSuchAlgorithmException | ServerException |
                 XmlParserException e) {
            throw new DocumentUploadException(e.getMessage());
        }
        return path;

    }

}
