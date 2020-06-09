package by.softclub.stm_api.repository;

import by.softclub.stm_api.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsernameEquals(String username);
}
