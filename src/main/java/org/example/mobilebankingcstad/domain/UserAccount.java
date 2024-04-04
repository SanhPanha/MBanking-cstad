package org.example.mobilebankingcstad.domain;


import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity(name = "user_accounts_tbl")
@Data
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
    private Boolean isDisabled;
    private Timestamp createdAt;
}
