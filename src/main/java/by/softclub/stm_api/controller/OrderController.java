package by.softclub.stm_api.controller;

import by.softclub.stm_api.dto.OrderDto;
import by.softclub.stm_api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "stm/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<OrderDto> getOrders() {
        return orderService.findAll();
    }

    //--Для валидации важно смотреть свою версию SpringBoot validation dependency в pom.xml,
    //--иначе валидация просто НЕ СРАБАТЫВАЕТ, хотя проект собирается без ошибок
    @PostMapping("/add")
    public String addOrder(@Valid @RequestBody OrderDto dto) {

        String result = "0";
        try {
            orderService.sendToKafka(dto);
        } catch (Exception e) {
            result = "-1: " + e.getMessage();
        }
        return result;
    }

}
