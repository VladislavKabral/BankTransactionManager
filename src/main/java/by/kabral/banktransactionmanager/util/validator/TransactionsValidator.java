package by.kabral.banktransactionmanager.util.validator;

import by.kabral.banktransactionmanager.dto.TransactionDto;
import by.kabral.banktransactionmanager.exception.InvalidRequestDataException;
import by.kabral.banktransactionmanager.util.ExpenseCategory;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static by.kabral.banktransactionmanager.util.Currency.*;
import static by.kabral.banktransactionmanager.util.Message.*;

@Component
public class TransactionsValidator implements Validator<TransactionDto> {

  @Override
  public boolean validate(TransactionDto transactionDto) throws InvalidRequestDataException {
    if (!currencyShortnames.contains(transactionDto.getCurrencyShortName())) {
      throw new InvalidRequestDataException(CURRENCY_SHORTNAME_IS_UNKNOWN);
    }

    String expenseCategory = transactionDto.getExpenseCategory();
    if ((!expenseCategory.equals(ExpenseCategory.PRODUCT.toString()))
            && (!expenseCategory.equals(ExpenseCategory.SERVICE.toString()))) {
      throw new InvalidRequestDataException(EXPENSE_CATEGORY_IS_UNKNOWN);
    }

    ZonedDateTime dateTime = transactionDto.getDateTime();
    if (dateTime.isAfter(ZonedDateTime.now(ZoneId.of(dateTime.getZone().toString())))) {
      throw new InvalidRequestDataException(TRANSACTION_DATETIME_IS_INVALID);
    }

    return true;
  }
}
