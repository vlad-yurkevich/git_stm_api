package by.softclub.stm_api.service;

import by.softclub.stm_api.config.jwt.JwtTokenProvider;
import by.softclub.stm_api.domain.User;
import by.softclub.stm_api.dto.LoginDto;
import by.softclub.stm_api.repository.UserRepository;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    @Qualifier("hazelcastInstance")
    private HazelcastInstance hazelcastInstance;

    private static final String CACHE_NAME = "sessions";

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsernameEquals(s);
    }

    @Override
    public UserDetails getUserByToken(String token) {
        IMap<Object, Object> userMap = hazelcastInstance.getMap(CACHE_NAME);
        return (User) userMap.get(token);
    }

    @Override
    public void addUserByToken(String token, User user) {
        IMap<Object, Object> userMap = hazelcastInstance.getMap(CACHE_NAME);
        userMap.put(token, user, 60, TimeUnit.MINUTES);
    }

    @Override
    public String login(LoginDto loginDto) {
        User user = (User) loadUserByUsername(loginDto.getLogin());

        if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            throw new RuntimeException();
        }

        String token = jwtTokenProvider.createToken(user.getUsername(), user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()));

        user.setPreviousToken(token);
        addUserByToken(token, user);

        return token;
    }
}
