package banking.service;

import banking.domain.entity.Account;

import java.util.List;

public interface AccountService {

    Account getBalance(long user_id);
    List<Account> allAccount();
    Account depositMoney(long user_id, long sum);
    Account withdrawMoney(long user_id, long sum);
}
