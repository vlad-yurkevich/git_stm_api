package by.softclub.stm_api.domain;

import by.softclub.stm_api.domain.enums.OrderState;
import by.softclub.stm_api.domain.enums.OrderType;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "STM_ORDER")
@Setter
@Getter
@Slf4j
@EntityListeners(AuditingEntityListener.class)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Currency sourceCurrency;

    @ManyToOne
    private Currency targetCurrency;

    @Enumerated(EnumType.STRING)
    private OrderType orderType;

    @Column(updatable = false)
    private BigDecimal amount;

    @Column(updatable = false)
    private BigDecimal fee;

    @Enumerated(EnumType.STRING)
    @Column(insertable = false)
    private OrderState orderState;
}
