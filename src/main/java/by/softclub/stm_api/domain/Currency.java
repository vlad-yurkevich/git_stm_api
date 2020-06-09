package by.softclub.stm_api.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@AllArgsConstructor
@NoArgsConstructor
public class Currency {

    @Id
    private int id;

    private String code;

    private String description;
}
