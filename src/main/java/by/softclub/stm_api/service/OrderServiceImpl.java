package by.softclub.stm_api.service;

import by.softclub.stm_api.domain.Currency;
import by.softclub.stm_api.domain.Order;
import by.softclub.stm_api.dto.OrderDto;
import by.softclub.stm_api.repository.CurrencyRepository;
import by.softclub.stm_api.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private KafkaTemplate<Long, OrderDto> kafkaTemplate;

    @PostConstruct
    private void prepareDB() {
        if (orderRepository.count() == 0) {
            currencyRepository.saveAndFlush(new Currency(1, "BYN", "Белорусский рубль"));
            currencyRepository.saveAndFlush(new Currency(2, "USD", "Доллар США"));
            currencyRepository.saveAndFlush(new Currency(3, "EUR", "Евро"));
            currencyRepository.saveAndFlush(new Currency(4, "BTC", "Биткоин"));
            currencyRepository.saveAndFlush(new Currency(5, "ETH", "Эфир"));
        }
    }

    @Override
    public List<OrderDto> findAll() {

        System.out.println(SecurityContextHolder.getContext().getAuthentication());

        return orderRepository.findAll().stream()
                .map(order -> {
                    var dto = new OrderDto();
                    dto.setAmount(order.getAmount());
                    dto.setFee(order.getFee());
                    dto.setId(order.getId());
                    dto.setSourceCurrency(order.getSourceCurrency().getCode());
                    dto.setTargetCurrency(order.getTargetCurrency().getCode());
                    return dto;
                }).collect(Collectors.toList());
    }

    @Override
    public void sendToKafka(OrderDto dto) {

        kafkaTemplate.send("stm_kafka", dto);

    }
}
