package org.example.mobilebankingcstad.features.files;

import jakarta.servlet.http.HttpServletRequest;
import org.example.mobilebankingcstad.features.files.dto.FileResponse;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {

    FileResponse uploadSingleFile(MultipartFile file, HttpServletRequest request);
    List<String> uploadMultipleFiles(MultipartFile[] files);

    ResponseEntity<Resource> serveFile(String filename, HttpServletRequest request);
    void deleteFile(String fileName);

}
