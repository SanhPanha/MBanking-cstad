package org.example.mobilebankingcstad.features.accounts;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.example.mobilebankingcstad.features.accounts.dto.AccountRequest;
import org.example.mobilebankingcstad.features.accounts.dto.AccountResponse;
import org.example.mobilebankingcstad.utils.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.example.mobilebankingcstad.utils.BaseResponse.ok;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/accounts")
public class AccountRestController {
    private final AccountService accountService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get All Accounts")
    public BaseResponse<List<AccountResponse>> getAllAccounts() {
        return BaseResponse.<List<AccountResponse>>ok()
                .setPayload(accountService.getAllAccounts());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create Account")
    public BaseResponse<AccountResponse> createAccount(@RequestBody AccountRequest request) {
        return BaseResponse.<AccountResponse>createSuccess()
                .setPayload(accountService.createAccount(request));
    }


    //    get account by id
    @GetMapping("/{id}")
    @Operation(summary = "Get Account by ID")
    public BaseResponse<AccountResponse> getAccountById(
            @Parameter(
                    description = "Account ID",
                    required = true,
                    example = "d7ab27d4-c7bb-4167-9d8f-7e56c75d4472"
            )
            @PathVariable String id) {
        return BaseResponse.<AccountResponse>ok()
                .setPayload(accountService.findAccountById(id));
    }

    @GetMapping("/account-number/{id}")
    @Operation(summary = "Get Account by Account Number")
    public BaseResponse<AccountResponse> getAccountByAccountNumber(
            @Parameter(
                    description = "account number",
                    required = true,
                    example = "string1"
            )
            @PathVariable("id") String accountNumber
    ) {
        return BaseResponse.<AccountResponse>ok()
                .setPayload(accountService.findAccountByAccountNumber(accountNumber));
    }

    @GetMapping("/user/{id}")
    @Operation(summary = "Get Account by User ID")
    public BaseResponse<List<AccountResponse>> findAccountByUserId(
            @Parameter(
                    description = "User ID",
                    required = true,
                    example = "17bcad2c-1758-411c-af2f-b8e165fcace1"
            )
            @PathVariable String id
    ) {
        return BaseResponse.<List<AccountResponse>>ok()
                .setPayload(accountService.findAccountsByUserId(id));
    }

    @PatchMapping("/{id}/disable")
    @Operation(summary = "Disable Account")
    public BaseResponse<AccountResponse> disableAccount(
            @Parameter(
                    description = "Account ID",
                    required = true,
                    example = "d7ab27d4-c7bb-4167-9d8f-7e56c75d4472"
            )
            @PathVariable String id) {
        return BaseResponse.<AccountResponse>disableSuccess();
    }

    @PatchMapping("/{id}/disable")
    @Operation(summary = "Disable Account")
    public BaseResponse<AccountResponse> enableAccount(
            @Parameter(
                    description = "Account ID",
                    required = true,
                    example = "d7ab27d4-c7bb-4167-9d8f-7e56c75d4472"
            )
            @PathVariable String id) {
        return BaseResponse.<AccountResponse>enableSuccess();
    }
}
