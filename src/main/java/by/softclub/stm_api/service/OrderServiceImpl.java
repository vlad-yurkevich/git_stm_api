package by.softclub.stm_api.service;

import by.softclub.stm_api.dto.OrderDto;
import by.softclub.stm_api.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private KafkaTemplate<Long, OrderDto> kafkaTemplate;

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

        //--Пока для тестов
        System.out.println("Send order " + dto);
        //kafkaTemplate.send("stm_kafka", dto);

    }
}
