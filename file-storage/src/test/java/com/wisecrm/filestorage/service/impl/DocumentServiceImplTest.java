package com.wisecrm.filestorage.service.impl;

import com.wisecrm.filestorage.dto.*;
import com.wisecrm.filestorage.dto.MinioProperties;
import com.wisecrm.filestorage.entity.*;
import com.wisecrm.filestorage.entity.Extension;
import com.wisecrm.filestorage.exceptions.*;
import com.wisecrm.filestorage.repository.*;
import com.wisecrm.filestorage.util.*;
import io.minio.*;
import lombok.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;
import org.springframework.http.*;
import org.springframework.mock.web.*;
import org.springframework.web.multipart.*;

import java.time.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DocumentServiceImplTest {


    @Mock
    private MinioClient minioClient;

    @Mock
    private MinioProperties minioProperties;


    @Mock
    private DocumentsRepository documentsRepository;

    @Mock
    private ExctensionRepository exctensionRepository;

    @InjectMocks
    private DocumentServiceImpl documentService;

    @Test
    @SneakyThrows
    void whenFileUploaded_thenVerifyStatus(){

        //given

        MockMultipartFile mockMultipartFile = new MockMultipartFile(
                "file",
                "hello.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World!".getBytes());

        List<MultipartFile> list = new ArrayList<>();

        list.add(mockMultipartFile);

        CreateDocRequest createDocRequest = CreateDocRequest.builder()
                .companyId(1L)
                .file(list)
                .category("Категория")
                .build();

        Extension extension = new Extension(1L, ".txt",1L);

        Document documentEntity = Document.builder()
                .name(FileProcessor.getFileName(mockMultipartFile.getOriginalFilename(), "."))
                .path("1/Категория/hello.txt")
                .size(100.0)
                .loadAt(LocalDate.now())
                .department_id(createDocRequest.getCompanyId())
                .extension(extension)
                .build();


        Optional<Extension> extensionOptional = Optional.of(extension);
        doReturn(extensionOptional).when(exctensionRepository).findByName(".txt");

        verify(minioClient).putObject(PutObjectArgs.builder()
                .stream(mockMultipartFile.getInputStream(), mockMultipartFile.getInputStream().available(), -1)
                .bucket("documents")
                .object("1/Категория/hello.txt")
                .build());

        doReturn(1L).when(documentsRepository).save(documentEntity);

        ObjectWriteResponse objectWriteResponse = new ObjectWriteResponse(null, minioProperties.getBucket(),null,null,null,null);





        //when

        var result = documentService.upload(createDocRequest);

        //then

        assertNotNull(result);
        assertDoesNotThrow(()-> new UnCorrectFileNameException(""));
        assertDoesNotThrow(()-> new NotSupportedExtension(""));
        assertEquals(result.size(), list.size());





    }

}