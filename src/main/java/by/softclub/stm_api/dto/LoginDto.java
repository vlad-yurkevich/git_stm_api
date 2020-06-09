package by.softclub.stm_api.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class LoginDto implements Serializable {
    private String login;
    private String password;
}
