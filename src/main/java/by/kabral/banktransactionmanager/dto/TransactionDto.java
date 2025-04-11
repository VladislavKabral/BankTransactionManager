package by.kabral.banktransactionmanager.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Data
public class TransactionDto {

  private String accountFrom;
  private String accountTo;
  private String currencyShortName;
  private BigDecimal sum;
  private String expenseCategory;
  private ZonedDateTime dateTime;
}
