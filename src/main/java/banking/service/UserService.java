package banking.service;

import banking.domain.entity.User;

import java.util.List;

public interface UserService {
    // return account_id
    long createUser(User user);

    List<User> allUsers();
}
