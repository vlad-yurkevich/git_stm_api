package by.softclub.stm_api.controller;

import by.softclub.stm_api.dto.OrderDto;
import by.softclub.stm_api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<OrderDto> getOrders() {
        return orderService.findAll();
    }

    @PostMapping("/add")
    public void addOrder(@Valid @RequestBody OrderDto dto) {

        orderService.sendToKafka(dto);
    }

}
