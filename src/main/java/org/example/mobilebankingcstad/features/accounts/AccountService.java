package org.example.mobilebankingcstad.features.accounts;

import org.example.mobilebankingcstad.features.accounts.dto.AccountRequest;
import org.example.mobilebankingcstad.features.accounts.dto.AccountResponse;

import java.util.List;

public interface AccountService {
    List<AccountResponse> getAllAccounts();
    AccountResponse createAccount(AccountRequest accountRequest);
    AccountResponse findAccountById(String id);
    AccountResponse findAccountByAccountNumber(String accountNumber);
    List<AccountResponse> findAccountsByUserId(String userId);
}
