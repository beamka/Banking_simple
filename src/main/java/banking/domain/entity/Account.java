package banking.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ACCOUNT")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACC_ID")
    private Long accId;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "AMOUNT")
    private Long amount;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "ACC_MOVE",
            joinColumns = {@JoinColumn(name = "ACC_ID")},
            inverseJoinColumns = {@JoinColumn(name = "MOVE_ID")})
    private List<Movement> movements = new ArrayList<>();

    @Override
    public String toString() {
        return "Account{" +
                "accId=" + accId +
                ", userId=" + userId +
                ", amount=" + amount +
                ", movements=" + movements +
                '}';
    }
}
