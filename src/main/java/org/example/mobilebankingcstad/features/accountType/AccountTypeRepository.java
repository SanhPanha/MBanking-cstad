package org.example.mobilebankingcstad.features.accountType;

import org.example.mobilebankingcstad.domain.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AccountTypeRepository extends JpaRepository<AccountType, String> {
}
