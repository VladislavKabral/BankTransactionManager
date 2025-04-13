package by.kabral.banktransactionmanager.dto;

import by.kabral.banktransactionmanager.util.ExpenseCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class LimitedTransactionDto {

  private UUID id;
  private String accountFrom;
  private String accountTo;
  private String currencyShortName;
  private BigDecimal sum;
  private ExpenseCategory expenseCategory;
  private ZonedDateTime dateTime;
  private BigDecimal limitSum;
  private ZonedDateTime limitDatetime;
  private String limitCurrencyShortname;
}
