package by.softclub.stm_api.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Immutable
@Entity
@Getter
@Setter
@Table(name = "STM_CURRENCY")
public class Currency {

    @Id
    private int id;

    private String code;

    private String description;
}
