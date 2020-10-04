package banking.api;

import banking.api.dto.AccountRequest;
import banking.api.dto.ErrorRequest;
import banking.api.dto.UserRequest;
import banking.domain.mapper.AccountMapper;
import banking.exception.InvalidException;
import banking.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService ;
    private final AccountMapper accountMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AccountRequest> allAccounts() {
        return accountMapper.mapToAccountRequestList(accountService.allAccount());
    }

    //* Client should be able to see the account balance and statement
    @GetMapping("/balance{user_id}")
    @ResponseStatus(HttpStatus.OK)
    public AccountRequest getBalanceAndMovement(@PathParam(value = "user_id") long user_id) {
        return accountMapper.mapToAccountRequest(accountService.getBalance(user_id));
    }

    //* Client should be able to deposit money
    @PostMapping("/deposit{user_id}{sum}")
    @ResponseStatus(HttpStatus.OK)
    public AccountRequest depositMoney(@PathParam(value = "user_id") long user_id,
                                       @PathParam(value = "sum") long sum) {
        return accountMapper.mapToAccountRequest(accountService.depositMoney(user_id, sum));
    }

    //* Client should be able to withdraw money
    @PostMapping("/withdraw{user_id}{sum}")
    @ResponseStatus(HttpStatus.OK)
    public AccountRequest withdrawMoney(@PathParam(value = "user_id") long user_id,
                                        @PathParam(value = "sum") long sum) {
        return accountMapper.mapToAccountRequest(accountService.withdrawMoney(user_id, sum));
    }

    @ExceptionHandler(InvalidException.class)
    @ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Bad Account")
    ErrorRequest onSaveError(Exception e) {
        return new ErrorRequest("400", e.toString());
    }

}
