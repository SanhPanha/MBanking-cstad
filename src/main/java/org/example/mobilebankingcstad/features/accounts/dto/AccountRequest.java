package org.example.mobilebankingcstad.features.accounts.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record AccountRequest(
//        String id,
        String accountNumber,
        String accountName,
        BigDecimal accountBalance,
        String accountType,
        String userId
) {
}
