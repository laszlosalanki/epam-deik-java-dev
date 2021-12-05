package com.epam.training.ticketservice.account;

import com.epam.training.ticketservice.data.Constants;
import com.epam.training.ticketservice.data.Role;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class AccountServiceImpl implements AccountService {

    private Account actualAccount;
    private AccountRepo accountRepo;

    public AccountServiceImpl(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    @Override
    public String signInPrivileged(String accountName, String accountPassword) {
        Account account = getAccountByAccountName(accountName);
        if (account != null) {
            if (account.getAccountPassword().equals(accountPassword)) {
                actualAccount = account;
                return null;
            }
            return Constants.LOGIN_FAILED_INCORRECT_CREDENTIALS;
        }
        return Constants.LOGIN_FAILED_INCORRECT_CREDENTIALS;
    }

    @Override
    public void signOut() {
        actualAccount = null;
    }

    @Override
    public String describeAccount() {
        if (actualAccount == null)
            return Constants.NOT_SIGNED_IN;
        if (actualAccount.getAccountRole().equals(Role.ADMIN))
            return new StringBuilder(Constants.SIGNED_IN_PRIVILEGED_ACCOUNT)
                    .append(actualAccount.getAccountName())
                    .append("'")
                    .toString();
        return Constants.NOT_SIGNED_IN_WITH_ADMIN_ROLE;
    }

    @Override
    public Account getActualAccount() {
        return actualAccount;
    }

    @PostConstruct
    public void initializeDefaultAccounts() {
        for (Account a : Constants.DEFAULT_ACCOUNTS)
            accountRepo.save(a);
    }

    public Account getAccountByAccountName(String accountName) {
        return accountRepo.searchAccountByAccountName(accountName);
    }
}
