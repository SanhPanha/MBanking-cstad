package org.example.mobilebankingcstad.mapper;


import org.example.mobilebankingcstad.domain.Account;
import org.example.mobilebankingcstad.domain.User;
import org.example.mobilebankingcstad.domain.UserAccount;
import org.example.mobilebankingcstad.features.accounts.dto.AccountRequest;
import org.example.mobilebankingcstad.features.accounts.dto.AccountResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface AccountMapper {

    @Mapping(target = "accountType", ignore = true)
    Account mapRequestToEntity(AccountRequest accountRequest);


//    @Mapping(target = "user", ignore = true)
//    @Mapping(target = "accountType", expression = "java(account.getAccountType().getName())")

    @Mapping(target = "id", source = "userAccount.account.id")
    @Mapping(target = "accountNumber", source = "userAccount.account.accountNumber")
    @Mapping(target = "accountName", source = "userAccount.account.accountName")
    @Mapping(target = "accountBalance", source = "userAccount.account.accountBalance")
    @Mapping(target = "user", source = "userAccount.user", qualifiedByName = "toUserResponse")
    @Mapping(target = "accountType", source = "userAccount.account.accountType.name")
    AccountResponse mapUserAccountToResponse(UserAccount userAccount);

//    @Mapping(target = "user", source = "user")
    void setUserForAccountResponse(@MappingTarget AccountResponse accountResponse, User user);
}
