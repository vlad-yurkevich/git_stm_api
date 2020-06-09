package by.softclub.stm_api.service;

import by.softclub.stm_api.domain.User;
import by.softclub.stm_api.dto.LoginDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthenticationService extends UserDetailsService {
    UserDetails getUserByToken(String uuid);
    void addUserByToken(String token, User user);
    String login(LoginDto loginDto);
}
