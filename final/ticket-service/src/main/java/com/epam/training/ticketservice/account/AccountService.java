package com.epam.training.ticketservice.account;

public interface AccountService {
    String signInPrivileged(String accountName, String accountPassword);
    String signOut();
    String describeAccount();
    Account getActualAccount();
}
