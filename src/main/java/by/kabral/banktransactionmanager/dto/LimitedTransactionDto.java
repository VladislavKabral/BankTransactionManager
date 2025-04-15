package by.kabral.banktransactionmanager.dto;

import by.kabral.banktransactionmanager.util.ExpenseCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

import static by.kabral.banktransactionmanager.util.Description.*;

@Schema(description = LIMITED_TRANSACTION_DESCRIPTION)
@Data
@AllArgsConstructor
@Builder
public class LimitedTransactionDto {

  @Schema(description = TRANSACTION_ID_DESCRIPTION, example = TRANSACTION_ID_EXAMPLE)
  private UUID id;

  @Schema(description = TRANSACTION_ACCOUNT_FROM_DESCRIPTION, example = TRANSACTION_ACCOUNT_FROM_EXAMPLE)
  private String accountFrom;

  @Schema(description = TRANSACTION_ACCOUNT_TO_DESCRIPTION, example = TRANSACTION_ACCOUNT_TO_EXAMPLE)
  private String accountTo;

  @Schema(description = TRANSACTION_CURRENCY_SHORTNAME_DESCRIPTION, example = TRANSACTION_CURRENCY_SHORTNAME_EXAMPLE)
  private String currencyShortName;

  @Schema(description = TRANSACTION_SUM_DESCRIPTION, example = TRANSACTION_SUM_EXAMPLE)
  private BigDecimal sum;

  @Schema(description = TRANSACTION_EXPENSE_CATEGORY_DESCRIPTION, example = TRANSACTION_EXPENSE_CATEGORY_EXAMPLE)
  private ExpenseCategory expenseCategory;

  @Schema(description = TRANSACTION_DATETIME_DESCRIPTION, example = TRANSACTION_DATETIME_EXAMPLE)
  private ZonedDateTime dateTime;

  @Schema(description = LIMITED_TRANSACTION_LIMIT_SUM_DESCRIPTION, example = LIMITED_TRANSACTION_LIMIT_SUM_EXAMPLE)
  private BigDecimal limitSum;

  @Schema(description = LIMITED_TRANSACTION_LIMIT_DATETIME_DESCRIPTION, example = LIMITED_TRANSACTION_LIMIT_DATETIME_EXAMPLE)
  private ZonedDateTime limitDatetime;

  @Schema(description = LIMITED_TRANSACTION_LIMIT_CURRENCY_SHORTNAME_DESCRIPTION, example = LIMITED_TRANSACTION_LIMIT_CURRENCY_SHORTNAME_EXAMPLE)
  private String limitCurrencyShortname;
}
