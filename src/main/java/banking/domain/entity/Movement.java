package banking.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "MOVEMENT")
public class Movement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MOVE_ID")
    private Long moveId;
    @Column(name = "DATE")
    private Date date;
    @Column(name = "DEAL_SUM")
    private Long dealSum;
    @Column(name = "BALANCE")
    private Long balance;

    @Override
    public String toString() {
        return "Movement{" +
                "moveId=" + moveId +
                ", date=" + date +
                ", dealSum=" + dealSum +
                ", balance=" + balance +
                '}';
    }
}
