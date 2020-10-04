package banking.api.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@RequiredArgsConstructor
public class MovementRequest {
    private Long moveId;
    private Date date;
    private Long dealSum;
    private Long balance;
}
