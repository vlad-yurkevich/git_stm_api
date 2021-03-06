package by.softclub.stm_api.controller;

import by.softclub.stm_api.dto.LoginDto;
import by.softclub.stm_api.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;

    //--ВАЖНО!!! Если используется баллансировщик, то сюда нужен путь вместе с псевдонимом!!!
    @PostMapping(value = "stm/login")
    public String login(@RequestBody LoginDto dto) {
        return authenticationService.login(dto);
    }
}
