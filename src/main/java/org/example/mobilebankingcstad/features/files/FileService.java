package org.example.mobilebankingcstad.features.files;

import org.example.mobilebankingcstad.features.files.dto.FileResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {

    FileResponse uploadSingleFile(MultipartFile file);
    List<String> uploadMultipleFiles(MultipartFile[] files);
    void deleteFile(String fileName);

}
