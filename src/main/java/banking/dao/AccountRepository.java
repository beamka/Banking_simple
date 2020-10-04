package banking.dao;

import banking.domain.entity.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account, Long > {
    List<Account> findAll();
    Optional<Account> findById(Long id);
    Optional<Account> findByUserId(Long userId);
}
