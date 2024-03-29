package org.example.mobilebankingcstad.features.files;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.mobilebankingcstad.features.files.dto.FileResponse;
import org.example.mobilebankingcstad.utils.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class FileRestController {

    private final FileService fileService;

    private String generateImageUrl(HttpServletRequest request, String fileName) {
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/images/" + fileName;
    }

    @PostMapping(value = "", consumes = "multipart/form-data")
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<FileResponse> uploadSingleFile(
            @RequestPart("file") MultipartFile file
    ) {
        return BaseResponse
                .<FileResponse>createSuccess()
                .setPayload(
                        fileService.uploadSingleFile(file)
                );
    }

    @PostMapping(value = "/multiple", consumes = "multipart/form-data")
    public List<String> uploadMultipleFiles(@RequestPart("files") MultipartFile[] files) {
        return fileService.uploadMultipleFiles(files);
    }

    @DeleteMapping(value = "/{fileName}")
    public String deleteFile(@PathVariable String fileName) {
        fileService.deleteFile(fileName);
        return "File deleted successfully";
    }
}
