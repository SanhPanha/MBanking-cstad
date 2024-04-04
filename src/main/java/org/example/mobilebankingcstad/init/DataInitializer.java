package org.example.mobilebankingcstad.init;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.example.mobilebankingcstad.domain.AccountType;
import org.example.mobilebankingcstad.domain.Role;
import org.example.mobilebankingcstad.features.accountType.AccountTypeRepository;
import org.example.mobilebankingcstad.features.roles.RoleRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


// populate database ( role with some data )
@Component
@RequiredArgsConstructor
public class DataInitializer {
    private final RoleRepository roleRepository;
    private final AccountTypeRepository accountTypeRepository;

    @PostConstruct
    void roleInit() {
        List<String> roles = List.of("ADMIN", "STUFF", "USER");
        if (roleRepository.findAll().isEmpty()) {
            for (var role : roles) {
                var roleObj = new Role();
                roleObj.setName(role);
                roleRepository.save(roleObj);
            }
        }

    }

    @PostConstruct
    void accountTypeInit() {
        List<AccountType> accountTypes = new ArrayList<>() {{
            add(new AccountType().setName("SAVINGS").setDescription("Savings Account"));
            add(new AccountType().setName("PAYROLL").setDescription("Payroll Account"));
            add(new AccountType().setName("CARDS").setDescription("Cards Account"));
        }};
        if (accountTypeRepository.findAll().isEmpty()) {
            accountTypeRepository.saveAll(accountTypes);
        }
    }
}

