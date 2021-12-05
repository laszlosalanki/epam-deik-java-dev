package com.epam.training.ticketservice.account;

import com.epam.training.ticketservice.data.Constants;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class AccountRommandHandler {

    AccountService accountService;

    public AccountRommandHandler(AccountService accountService) {
        this.accountService = accountService;
    }

    @ShellMethod(value = Constants.SIGN_IN_PRIVILEGED_METHOD_VALUE, key = Constants.SIGN_IN_PRIVILEGED_COMMAND)
    public String signInPrivileged(String accountName, String accountPassword) {
        return accountService.signInPrivileged(accountName, accountPassword);
    }

    @ShellMethod(value = Constants.SIGN_OUT_METHOD_VALUE, key = Constants.SIGN_OUT_COMMAND)
    public void signOut() {
        accountService.signOut();
    }

    @ShellMethod(value = Constants.DESCRIBE_ACCOUNT_METHOD_VALUE, key = Constants.DESCRIBE_ACCOUNT_COMMAND)
    public String describeAccount() {
        return accountService.describeAccount();
    }
}
