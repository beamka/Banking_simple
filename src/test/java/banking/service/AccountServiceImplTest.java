package banking.service;

import banking.dao.AccountRepository;
import banking.dao.MovementRepository;
import banking.domain.entity.Account;
import banking.domain.entity.Movement;
import banking.service.AccountService;
import banking.service.impl.AccountServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;

import static java.util.Collections.singletonList;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceImplTest {
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private MovementRepository movementRepository;

    private AccountService accountService;

    @Before
    public void init() {
        accountService = new AccountServiceImpl(accountRepository, movementRepository);

    }

    @Test
    public void ifNoAccounts() {
        assertThat(accountService.allAccount(), is(empty()));
    }

    @Test
    public void depositMoneyShouldBeUpdateAccountAmount() {
        Account account = new Account();
        account.setUserId(1L);
        account.setAmount(10L);
        Movement movement = new Movement();
        when(accountRepository.findByUserId(1L)).thenReturn(java.util.Optional.of(account));
        when(movementRepository.save(movement)).thenReturn(movement);
        when(accountRepository.save(account)).thenReturn(account);
        assertThat(accountService.depositMoney(1, 10).getAmount(), is(20L));
    }

    @Test
    public void withdrawMoneyShouldBeUpdateAccountAmount() {
        Account account = new Account();
        account.setUserId(1L);
        account.setAmount(10L);
        Movement movement = new Movement();
        when(accountRepository.findByUserId(1L)).thenReturn(java.util.Optional.of(account));
        when(movementRepository.save(movement)).thenReturn(movement);
        when(accountRepository.save(account)).thenReturn(account);
        assertThat(accountService.withdrawMoney(1, 10).getAmount(), is(0L));
    }
}


