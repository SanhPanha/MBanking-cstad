package org.example.mobilebankingcstad.features.accounts;

import lombok.RequiredArgsConstructor;
import org.example.mobilebankingcstad.domain.Account;
import org.example.mobilebankingcstad.domain.UserAccount;
import org.example.mobilebankingcstad.features.accountType.AccountTypeRepository;
import org.example.mobilebankingcstad.features.accounts.dto.AccountRequest;
import org.example.mobilebankingcstad.features.accounts.dto.AccountResponse;
import org.example.mobilebankingcstad.features.user.UserRepository;
import org.example.mobilebankingcstad.features.user.dto.UserResponse;
import org.example.mobilebankingcstad.features.userAccount.UserAccountRepository;
import org.example.mobilebankingcstad.mapper.AccountMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountMapper accountMapper;
    private final AccountTypeRepository accountTypeRepository;
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final UserAccountRepository userAccountRepository;

    @Override
    public List<AccountResponse> getAllAccounts() {
        return userAccountRepository.findAll()
                .stream()
                .map(accountMapper::mapUserAccountToResponse)
                .toList();
    }

    @Override
    public AccountResponse createAccount(AccountRequest request) {
//        Check if account type exists

        var accountType = accountTypeRepository
                .findByName(request.accountType())
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.BAD_REQUEST,
                                "Account Type: <" + request.accountType() + "> " + "does not exist!"
                        )
                );

        var owner = userRepository.findById(request.userId())
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.BAD_REQUEST,
                                "User with ID: <" + request.userId() + "> " + "does not exist!"
                        )
                );


        // check if one user have no more than five account
        if (userAccountRepository.countAccountsByUserId(request.userId()) >= 5) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "User with ID: <" + request.userId() + "> " + "already have 5 accounts!"
            );
        }


        var account = accountMapper.mapRequestToEntity(request);
        account.setAccountType(accountType);

        UserAccount userAccount = new UserAccount()
                .setAccount(account)
                .setUser(owner)
                .setIsDisabled(false);
        userAccountRepository.save(userAccount);
        var accountResponse = accountMapper.mapUserAccountToResponse(userAccount);
        accountMapper.setUserForAccountResponse(accountResponse, owner);
        return accountResponse;
    }

    @Override
    public AccountResponse findAccountById(String id) {
        var userAccount = userAccountRepository.findByAccount_Id(id)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Account with ID: <" + id + "> " + "not found!"
                        )
                );
        return accountMapper.mapUserAccountToResponse(userAccount);
    }

    @Override
    public AccountResponse findAccountByAccountNumber(String accountNumber) {
        var accountByAccountNumber = userAccountRepository
                .findByAccount_AccountNumber(accountNumber)
                .orElseThrow(
                        () ->
                                new ResponseStatusException(
                                        HttpStatus.NOT_FOUND,
                                        "Account with Account Number: <" + accountNumber + "> " + "not found!"
                                )
                );
        return accountMapper.mapUserAccountToResponse(accountByAccountNumber);
    }

    @Override
    public List<AccountResponse> findAccountsByUserId(String userId) {
        var userAccountByUserId = userAccountRepository.findByUser_Id(userId);

        return userAccountByUserId.stream()
                .map(accountMapper::mapUserAccountToResponse)
                .toList();
    }

    @Override
    public UserResponse disableAccount(String id) {
        return null;
    }

    @Override
    public UserResponse enableAccount(String id) {
        return null;
    }
}
