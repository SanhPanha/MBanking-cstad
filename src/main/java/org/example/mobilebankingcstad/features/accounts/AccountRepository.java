package org.example.mobilebankingcstad.features.accounts;


import org.example.mobilebankingcstad.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, String>{
}
