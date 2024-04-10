package org.example.mobilebankingcstad.features.userAccount;

import org.example.mobilebankingcstad.domain.User;
import org.example.mobilebankingcstad.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, String> {
//    method for counting the number of accounts by user id

    //    @Modifying
//    @Transactional(readOnly = true)
    @Query("SELECT COUNT(ua) FROM user_accounts_tbl ua WHERE ua.user.id = ?1")
    int countAccountsByUserId(@Param("userId") String userId);

    Optional<UserAccount> findByAccount_Id(String accountId);

    List<UserAccount> findByUser_Id(String id);

    Optional<UserAccount> findByAccount_AccountNumber(String accountNumber);

    Long countByUser(User user); // derived query
}
