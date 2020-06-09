package by.softclub.stm_api.dto;

import by.softclub.stm_api.validator.OrderDtoConstraint;
import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@OrderDtoConstraint
public class OrderDto {

    private int id;

    private String sourceCurrency;

    private String targetCurrency;

    private BigDecimal amount;

    private BigDecimal fee;

    private String userEmail;
}
