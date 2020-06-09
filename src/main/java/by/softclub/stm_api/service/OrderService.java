package by.softclub.stm_api.service;

import by.softclub.stm_api.dto.OrderDto;

import java.util.List;

public interface OrderService {
    List<OrderDto> findAll();

    void sendToKafka(OrderDto dto);

}
