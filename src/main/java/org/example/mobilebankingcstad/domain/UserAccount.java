package org.example.mobilebankingcstad.domain;


import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.sql.Timestamp;

@Entity(name = "user_accounts_tbl")
@Data
@Accessors(chain = true)
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "account_id",referencedColumnName = "id")
    private Account account;
    private Boolean isDisabled;
    private Timestamp createdAt;
}
