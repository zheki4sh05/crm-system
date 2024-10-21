package com.wisecrm.filestorage.service.interfaces;

import com.wisecrm.filestorage.dto.*;
import com.wisecrm.filestorage.exceptions.*;

import java.util.*;

public interface IDocumentControl {
   List<DocumentDto> upload(CreateDocRequest createDocRequest) throws DocumentUploadException, UnCorrectFileNameException, NotSupportedExtension;


}
