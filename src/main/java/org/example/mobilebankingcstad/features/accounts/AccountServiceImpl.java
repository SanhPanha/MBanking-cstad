package org.example.mobilebankingcstad.features.accounts;

import lombok.RequiredArgsConstructor;
import org.example.mobilebankingcstad.features.accountType.AccountTypeRepository;
import org.example.mobilebankingcstad.features.accounts.dto.AccountRequest;
import org.example.mobilebankingcstad.features.accounts.dto.AccountResponse;
import org.example.mobilebankingcstad.features.user.UserRepository;
import org.example.mobilebankingcstad.mapper.AccountMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{
    private final AccountMapper accountMapper;
    private final AccountTypeRepository accountTypeRepository;
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    @Override
    public List<AccountResponse> getAllAccounts() {
        return null;
    }

    @Override
    public AccountResponse createAccount(AccountRequest accountRequest) {
        return null;
    }

    @Override
    public AccountResponse findAccountById(String id) {
        return null;
    }

    @Override
    public AccountResponse findAccountByAccountNumber(String accountNumber) {
        return null;
    }

    @Override
    public List<AccountResponse> findAccountsByUserId(String userId) {
        return null;
    }


}
