package by.softclub.stm_api;

import by.softclub.stm_api.domain.Order;
import by.softclub.stm_api.domain.enums.OrderState;
import by.softclub.stm_api.domain.enums.OrderType;
import by.softclub.stm_api.repository.CurrencyRepository;
import by.softclub.stm_api.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;

@SpringBootTest
class StmApiApplicationTests {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void generatePassword() throws Exception {
        String res = passwordEncoder.encode("admin");

        System.out.println(res);

        Assertions.assertTrue(passwordEncoder.matches("admin", res));
    }

    @Test
    void addOrder() {
        var order = new Order();

        currencyRepository.findById(1).ifPresent(
                order::setSourceCurrency
        );

        currencyRepository.findById(2).ifPresent(
                order::setTargetCurrency
        );


        order.setAmount(new BigDecimal("100"));
        order.setFee(new BigDecimal("0.02"));

        order.setOrderState(OrderState.CREATED);
        order.setOrderType(OrderType.BUY);

        orderRepository.saveAndFlush(order);

    }

}
