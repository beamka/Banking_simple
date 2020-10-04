package banking.service.impl;

import banking.dao.AccountRepository;
import banking.dao.MovementRepository;
import banking.domain.entity.Account;
import banking.domain.entity.Movement;
import banking.domain.entity.User;
import banking.exception.InvalidException;
import banking.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final MovementRepository movementRepository;

    public AccountServiceImpl(AccountRepository accountRepository, MovementRepository movementRepository) {
        this.accountRepository = accountRepository;
        this.movementRepository = movementRepository;
    }

    @Override
    public Account getBalance(long user_id){
        Account account = accountRepository.findByUserId(user_id).orElse(null);
        if(account != null){
            return account;
        } else throw new InvalidException("Account of userId: [" + user_id + "] not found!");
    }

    @Override
    public List<Account> allAccount(){
        return accountRepository.findAll();
    }

    @Override
    public Account depositMoney(long user_id, long sum){
        Account account = getBalance(user_id);
        long newBalance = account.getAmount() + sum;
        account.getMovements().add(newMove(sum, newBalance));
        account.setAmount(newBalance);
        return accountRepository.save(account);
    }

    @Override
    public Account withdrawMoney(long user_id, long sum){
        Account account = getBalance(user_id);
        long newBalance = account.getAmount() - sum;
        account.getMovements().add(newMove(Math.negateExact(sum), newBalance));
        account.setAmount(newBalance);
        return accountRepository.save(account);
    }

    private Movement newMove(long sum, long newBalance){
        Movement movement = new Movement ();
        movement.setDate(new Date(System.currentTimeMillis()));
        movement.setDealSum(sum);
        movement.setBalance(newBalance);
        return movementRepository.save(movement);
    }
}
