package by.softclub.stm_api.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = OrderDtoValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface OrderDtoConstraint {
    String message() default "Object OrderDto is not valid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
