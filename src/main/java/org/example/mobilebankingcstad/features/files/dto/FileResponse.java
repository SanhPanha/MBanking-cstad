package org.example.mobilebankingcstad.features.files.dto;


import lombok.Builder;

@Builder
public record FileResponse(
        String fileName,
        String fullUrl,
        String downloadUrl,
        String fileType,
        float fileSize
) {
}
