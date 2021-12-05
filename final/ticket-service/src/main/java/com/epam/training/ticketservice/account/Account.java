package com.epam.training.ticketservice.account;

import com.epam.training.ticketservice.data.Role;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Account {

    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String accountName;
    private String accountPassword;

    @Enumerated(EnumType.STRING)
    private Role accountRole;

    public Account() {}

    public Account(String accountName, String accountPassword, Role accountRole) {
        this.accountName = accountName;
        this.accountPassword = accountPassword;
        this.accountRole = accountRole;
    }

    public Account(final AccountBuilder accountBuilder) {
        this.accountName = accountBuilder.accountName;
        this.accountPassword = accountBuilder.accountPassword;
        this.accountRole = accountBuilder.accountRole;
    }

    public static AccountBuilder builder() { return new AccountBuilder(); }

    public String getAccountName() {
        return accountName;
    }

    public String getAccountPassword() {
        return accountPassword;
    }

    public Role getAccountRole() {
        return accountRole;
    }

    @Override
    public String toString() {
        return accountName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(accountName, account.accountName) && Objects.equals(accountPassword, account.accountPassword) && accountRole == account.accountRole;
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountName, accountPassword, accountRole);
    }

    public static final class AccountBuilder {
        private String accountName;
        private String accountPassword;
        private Role accountRole;

        private AccountBuilder() {}

        public AccountBuilder withAccountName(String accountName) {
            this.accountName = accountName;
            return this;
        }

        public AccountBuilder withAccountPassword(String accountPassword) {
            this.accountPassword = accountPassword;
            return this;
        }

        public AccountBuilder withAccountRole(Role accountRole) {
            this.accountRole = accountRole;
            return this;
        }

        public Account build() { return new Account(this); }
    }
}
