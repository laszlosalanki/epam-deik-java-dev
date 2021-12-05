package com.epam.training.ticketservice.account;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepo extends JpaRepository<Account, Long> {
    Account searchAccountByAccountName(String accountName);
}
