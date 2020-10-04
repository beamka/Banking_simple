package banking.service.impl;

import banking.dao.AccountRepository;
import banking.dao.UserRepository;
import banking.domain.entity.Account;
import banking.domain.entity.User;
import banking.exception.InvalidException;
import banking.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    @Override
    public long createUser(User user){
        if(userRepository.findByEmail(user.getEmail()).orElse(null) == null){
            long user_id = userRepository.save(user).getUserID();
            generateAccountForUser(user_id);
            return user_id;
        } else throw new InvalidException("The email of the user already exists!");
    }

    private Account generateAccountForUser(long user_id){
        Account account = new Account();
        account.setAmount(0L);
        account.setUserId(user_id);
        return accountRepository.save(account);
    }

    @Override
    public List<User> allUsers(){
        return userRepository.findAll();
    }
}
