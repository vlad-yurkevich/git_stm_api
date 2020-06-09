package by.softclub.stm_api.validator;

import by.softclub.stm_api.dto.OrderDto;
import by.softclub.stm_api.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderDtoValidator implements ConstraintValidator<OrderDtoConstraint, OrderDto> {

    private String baseMessage;
    private List<String> messageList = new ArrayList<>();

    @Autowired
    private CurrencyRepository currencyRepository;

    @Override
    public void initialize(OrderDtoConstraint constraintAnnotation) {
        this.baseMessage = "ERROR_CLASS_DATA";
    }

    @Override
    public boolean isValid(OrderDto orderDto, ConstraintValidatorContext constraintValidatorContext) {
        boolean result = true;
        messageList.clear();
        if (!currencyRepository.findByCode(orderDto.getSourceCurrency()).isPresent()) {
            messageList.add("Source currency not found in DataBase");
        }
        if (!currencyRepository.findByCode(orderDto.getTargetCurrency()).isPresent()) {
            messageList.add("Target currency not found in DataBase");
        }
        if (orderDto.getAmount().compareTo(new BigDecimal(0)) < 0) {
            messageList.add("Amount must be > 0");
        }

        if (messageList.size() > 0)
        {
            result = false;
            constraintValidatorContext.buildConstraintViolationWithTemplate(messageList.toString()).addConstraintViolation();
        }

        return result;
    }
}
