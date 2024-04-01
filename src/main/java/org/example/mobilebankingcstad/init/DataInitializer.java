package org.example.mobilebankingcstad.init;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.PrePersist;
import lombok.RequiredArgsConstructor;
import org.example.mobilebankingcstad.domain.Role;
import org.example.mobilebankingcstad.features.roles.RoleRepository;
import org.springframework.stereotype.Component;

import java.util.List;


// populate database ( role with some data )
@Component
@RequiredArgsConstructor
public class DataInitializer {
    private final RoleRepository roleRepository;
    @PostConstruct
    void roleInit(){
        List<String> roles = List.of("ADMIN","STUFF","USER");
        if(roleRepository.findAll().isEmpty()){
            for(var role : roles){
                var roleObj = new Role();
                roleObj.setName(role);
                roleRepository.save(roleObj);
            }
        }

    }
}

