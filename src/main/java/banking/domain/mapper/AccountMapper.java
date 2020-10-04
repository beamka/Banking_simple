package banking.domain.mapper;

import banking.api.dto.AccountRequest;
import banking.api.dto.UserRequest;
import banking.dao.UserRepository;
import banking.domain.entity.Account;
import banking.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AccountMapper {
    private final MovementMapper movementMapper;

    public Account mapToAccount(AccountRequest accountRequest){
        Account account = new Account();
        account.setAccId(accountRequest.getAccId());
        account.setUserId(accountRequest.getUserId());
        account.setAmount(accountRequest.getAmount());
        account.setMovements(movementMapper.mapToMovementList(accountRequest.getMovements()));
        return account;
    }

    public AccountRequest mapToAccountRequest(Account account){
        AccountRequest accountRequest = new AccountRequest();
        accountRequest.setAccId(account.getAccId());
        accountRequest.setUserId(account.getUserId());
        accountRequest.setAmount(account.getAmount());
        accountRequest.setMovements(movementMapper.mapToMovementRequestList(account.getMovements()));
        return accountRequest;
    }

    public List<AccountRequest> mapToAccountRequestList(List<Account> accounts){
        List<AccountRequest> result = new ArrayList();
        accounts.forEach(account -> result.add(mapToAccountRequest(account)));
        return result;
    }

    public List<Account> mapToUserList(List<AccountRequest> accountsRequest){
        List<Account> result = new ArrayList();
        accountsRequest.forEach(account -> result.add(mapToAccount(account)));
        return result;
    }
}
