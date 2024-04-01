package org.example.mobilebankingcstad.features.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.Set;

@Builder
public record UserRequest(
        @NotNull
        @NotEmpty
        String username,
        @NotEmpty
        String fullName,
        @NotEmpty
        String gender,
        @Size(min = 6, max = 6, message = "Pin should be 6 characters")
        String pin,
        @Email(message = "Email should be valid")
        String email,
        String password,
        String profileImage,
        String phoneNumber,
        String cityOrProvince,
        String khanOrDistrict,
        String sangkatOrCommune,
        String employeeType,
        String companyName,
        String mainSourceOfIncome,
        BigDecimal monthlyIncomeRange,
        String studentCardId,
        Set<String> roles
) {
}
