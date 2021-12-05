package com.epam.training.ticketservice.account;

import com.epam.training.ticketservice.data.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class TestAccountServiceImpl {

    private AccountServiceImpl underTest;

    @Mock
    private AccountRepo accountRepo;

    @BeforeEach
    public void setUnderTest() {
        MockitoAnnotations.openMocks(this);
        underTest = new AccountServiceImpl(accountRepo);
    }
    
    @Test
    public void testSignInPrivilegedShouldUpdateCurrentAccountToPrivilegedWhenGivenCorrectCredentials() {
        //Given
        final Account expectedAccount = new Account("admin","admin", Role.ADMIN);
        BDDMockito.given(accountRepo.searchAccountByAccountName("admin")).willReturn(expectedAccount);

        //When
        final String result = underTest.signInPrivileged("admin","admin");
        final Account resultAccount = underTest.getActualAccount();

        //Then
        Assertions.assertNull(result);
        Assertions.assertEquals(expectedAccount,resultAccount);
    }

    @Test
    public void testSignInPrivilegedShouldNotUpdateCurrentAccountToPrivilegedWhenGivenInCorrectCredentials() {
        //Given
        final Account account = new Account("admin","admin", Role.ADMIN);
        BDDMockito.given(accountRepo.searchAccountByAccountName("admin")).willReturn(account);
        final String expected = "Login failed due to incorrect credentials";

        //When
        final String result = underTest.signInPrivileged("admin","adminPass");

        //Then
        Assertions.assertEquals(expected,result);
    }

    @Test
    public void testDescribeAccountShouldReturnCorrectStringWhenSignedInPrivilegedAccount() {
        //Given
        final Account account = new Account("admin","admin", Role.ADMIN);
        BDDMockito.given(accountRepo.searchAccountByAccountName("admin")).willReturn(account);
        underTest.signInPrivileged("admin","admin");
        final String expected = "Signed in with privileged account 'admin'";

        //When
        final String result = underTest.describeAccount();

        //Then
        Assertions.assertEquals(expected,result);
    }

    @Test
    public void testDescribeAccountShouldReturnCorrectStringWhenSignedIntoANonPrivilegedAccount() {
        //Given
        final Account account = new Account("user","user", Role.USER);
        BDDMockito.given(accountRepo.searchAccountByAccountName("user")).willReturn(account);
        underTest.signInPrivileged("user","user");
        final String expected = "You are not an ADMIN";

        //When
        final String result = underTest.describeAccount();

        //Then
        Assertions.assertEquals(expected,result);
    }
    @Test
    public void testDescribeAccountShouldReturnCorrectStringWhenNotSignedIn() {
        //Given
        BDDMockito.given(accountRepo.searchAccountByAccountName("admin")).willReturn(null);
        underTest.signInPrivileged("admin","admin");
        final String expected = "You are not signed in";

        //When
        final String result = underTest.describeAccount();

        //Then
        Assertions.assertEquals(expected,result);
    }

    @Test
    public void testGetActualAccountShouldReturnLoggedInAccount() {
        //Given
        final Account account = new Account("admin","admin", Role.ADMIN);
        BDDMockito.given(accountRepo.searchAccountByAccountName("admin")).willReturn(account);
        underTest.signInPrivileged("admin","admin");
        final Account expected = account;

        //When
        final Account result = underTest.getActualAccount();

        //Then
        Assertions.assertEquals(expected,result);
    }
}
