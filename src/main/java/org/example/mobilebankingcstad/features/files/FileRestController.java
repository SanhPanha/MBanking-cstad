package org.example.mobilebankingcstad.features.files;


import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.mobilebankingcstad.features.files.dto.FileResponse;
import org.example.mobilebankingcstad.utils.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class FileRestController {

    private final FileService fileService;
    @PostMapping(value = "", consumes = "multipart/form-data")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Upload single file")
    public BaseResponse<FileResponse> uploadSingleFile(
            @RequestPart("file") MultipartFile file, HttpServletRequest request
    ) {
        return BaseResponse
                .<FileResponse>createSuccess()
                .setPayload(
                        fileService.uploadSingleFile(file, request)
                );
    }


    @PostMapping(value = "/multiple", consumes = "multipart/form-data")
    @Operation(summary = "Upload multiple files")
    public List<String> uploadMultipleFiles(@RequestPart("files") MultipartFile[] files) {
        return fileService.uploadMultipleFiles(files);
    }

    //    localhost:8888/api/v1/files/download/fskfjdkjsfdf.jpg

    //    @Hidden   // use this hide your method @GET.... from the swagger ui
    @GetMapping("/download/{fileName}")
    @Operation(summary = "Download file")
   public ResponseEntity<?> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        return fileService.serveFile(fileName, request);

    }
    @DeleteMapping(value = "/{fileName}")
    @Operation(summary = "Delete file")
    public String deleteFile(@PathVariable String fileName) {
        fileService.deleteFile(fileName);
        return "File deleted successfully";
    }
}
