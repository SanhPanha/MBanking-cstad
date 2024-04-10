package org.example.mobilebankingcstad.features.accounts.dto;

import lombok.Builder;
import org.example.mobilebankingcstad.features.user.dto.UserResponse;

import java.math.BigDecimal;


@Builder
public record AccountResponse (
        String id,
        String accountNumber,
        String accountName,
        BigDecimal accountBalance,
        UserResponse user,
        String accountType

){
}
