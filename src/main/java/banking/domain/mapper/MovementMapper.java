package banking.domain.mapper;

import banking.api.dto.AccountRequest;
import banking.api.dto.MovementRequest;
import banking.api.dto.UserRequest;
import banking.domain.entity.Account;
import banking.domain.entity.Movement;
import banking.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MovementMapper {
    public Movement mapToMovement(MovementRequest movementRequest){
        Movement movement = new Movement();
        movement.setMoveId(movementRequest.getMoveId());
        movement.setDate(movementRequest.getDate());
        movement.setDealSum(movementRequest.getDealSum());
        movement.setBalance(movementRequest.getBalance());
        return movement;
    }

    public MovementRequest mapToMovementRequest(Movement movement){
        MovementRequest movementRequest = new MovementRequest();
        movementRequest.setMoveId(movement.getMoveId());
        movementRequest.setDate(movement.getDate());
        movementRequest.setDealSum(movement.getDealSum());
        movementRequest.setBalance(movement.getBalance());
        return movementRequest;
    }

    public List<MovementRequest> mapToMovementRequestList(List<Movement> movements){
        List<MovementRequest> result = new ArrayList();
        movements.forEach(movement -> result.add(mapToMovementRequest(movement)));
        return result;
    }

    public List<Movement> mapToMovementList(List<MovementRequest> movementsRequest){
        List<Movement> result = new ArrayList();
        movementsRequest.forEach(movement -> result.add(mapToMovement(movement)));
        return result;
    }
}
