package com.epam.training.ticketservice.account;

public interface AccountService {

    String signInPrivileged(String accountName, String accountPassword);

    void signOut();

    String describeAccount();

    Account getActualAccount();
}
