package banking.api.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class AccountRequest {
    private Long accId;
    private Long userId;
    private Long amount;
    private List<MovementRequest> movements = new ArrayList<>();
}
