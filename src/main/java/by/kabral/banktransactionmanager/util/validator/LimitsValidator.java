package by.kabral.banktransactionmanager.util.validator;

import by.kabral.banktransactionmanager.dto.LimitDto;
import by.kabral.banktransactionmanager.exception.InvalidRequestDataException;
import by.kabral.banktransactionmanager.util.ExpenseCategory;
import org.springframework.stereotype.Component;

import static by.kabral.banktransactionmanager.util.Message.*;

@Component
public class LimitsValidator implements Validator<LimitDto> {

  @Override
  public void validate(LimitDto limitDto) throws InvalidRequestDataException {
    String type = limitDto.getType();
    if ((!type.equals(ExpenseCategory.PRODUCT.toString()))
            && (!type.equals(ExpenseCategory.SERVICE.toString()))) {
      throw new InvalidRequestDataException(LIMIT_TYPE_IS_UNKNOWN);
    }
  }
}
