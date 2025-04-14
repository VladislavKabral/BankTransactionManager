package by.kabral.banktransactionmanager.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

import static by.kabral.banktransactionmanager.util.Message.*;
import static by.kabral.banktransactionmanager.util.Constant.*;
import static by.kabral.banktransactionmanager.util.Regex.*;

@Data
@Builder
public class TransactionDto {

  private UUID id;

  @NotBlank(message = ACCOUNT_FROM_IS_BLANK)
  @Size(min = ACCOUNT_NAME_LENGTH, max = ACCOUNT_NAME_LENGTH, message = ACCOUNT_NAME_LENGTH_IS_INVALID)
  @Pattern(regexp = ACCOUNT_NAME_REGEX, message = ACCOUNT_NAME_IS_INVALID)
  private String accountFrom;

  @NotBlank(message = ACCOUNT_TO_IS_BLANK)
  @Size(min = ACCOUNT_NAME_LENGTH, max = ACCOUNT_NAME_LENGTH, message = ACCOUNT_NAME_LENGTH_IS_INVALID)
  @Pattern(regexp = ACCOUNT_NAME_REGEX, message = ACCOUNT_NAME_IS_INVALID)
  private String accountTo;

  @NotBlank(message = CURRENCY_SHORTNAME_IS_BLANK)
  @Size(min = CURRENCY_SHORTNAME_LENGTH, max = CURRENCY_SHORTNAME_LENGTH, message = CURRENCY_SHORTNAME_LENGTH_IS_INVALID)
  @Pattern(regexp = CURRENCY_SHORTNAME_REGEX, message = CURRENCY_SHORTNAME_IS_INVALID)
  private String currencyShortName;

  @NotNull(message = SUM_IS_NULL)
  @DecimalMin(value = TRANSACTION_SUM_MIN_VALUE, inclusive = false, message = SUM_IS_INVALID)
  private BigDecimal sum;

  @NotBlank(message = EXPENSE_CATEGORY_IS_BLANK)
  @Size(min = EXPENSE_CATEGORY_LENGTH, max = EXPENSE_CATEGORY_LENGTH, message = EXPENSE_CATEGORY_LENGTH_IS_INVALID)
  @Pattern(regexp = EXPENSE_CATEGORY_REGEX, message = EXPENSE_CATEGORY_IS_INVALID)
  private String expenseCategory;

  @NotNull(message = DATETIME_IS_NULL)
  private ZonedDateTime dateTime;

  private boolean isLimitExceeded;
}
