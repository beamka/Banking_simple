package banking.dao;

import banking.domain.entity.Movement;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MovementRepository extends CrudRepository<Movement, Long > {
    List<Movement> findAll();
    Optional<Movement> findById(Long id);
}
