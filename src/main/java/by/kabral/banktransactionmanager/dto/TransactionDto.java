package by.kabral.banktransactionmanager.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

import static by.kabral.banktransactionmanager.util.Message.*;
import static by.kabral.banktransactionmanager.util.Constant.*;
import static by.kabral.banktransactionmanager.util.Regex.*;
import static by.kabral.banktransactionmanager.util.Description.*;

@Schema(description = TRANSACTION_DESCRIPTION)
@Data
@Builder
public class TransactionDto {

  @Schema(description = TRANSACTION_ID_DESCRIPTION, example = TRANSACTION_ID_EXAMPLE)
  private UUID id;

  @Schema(description = TRANSACTION_ACCOUNT_FROM_DESCRIPTION, example = TRANSACTION_ACCOUNT_FROM_EXAMPLE)
  @NotBlank(message = ACCOUNT_FROM_IS_BLANK)
  @Size(min = ACCOUNT_NAME_LENGTH, max = ACCOUNT_NAME_LENGTH, message = ACCOUNT_NAME_LENGTH_IS_INVALID)
  @Pattern(regexp = ACCOUNT_NAME_REGEX, message = ACCOUNT_NAME_IS_INVALID)
  private String accountFrom;

  @Schema(description = TRANSACTION_ACCOUNT_TO_DESCRIPTION, example = TRANSACTION_ACCOUNT_TO_EXAMPLE)
  @NotBlank(message = ACCOUNT_TO_IS_BLANK)
  @Size(min = ACCOUNT_NAME_LENGTH, max = ACCOUNT_NAME_LENGTH, message = ACCOUNT_NAME_LENGTH_IS_INVALID)
  @Pattern(regexp = ACCOUNT_NAME_REGEX, message = ACCOUNT_NAME_IS_INVALID)
  private String accountTo;

  @Schema(description = TRANSACTION_CURRENCY_SHORTNAME_DESCRIPTION, example = TRANSACTION_CURRENCY_SHORTNAME_EXAMPLE)
  @NotBlank(message = CURRENCY_SHORTNAME_IS_BLANK)
  @Size(min = CURRENCY_SHORTNAME_LENGTH, max = CURRENCY_SHORTNAME_LENGTH, message = CURRENCY_SHORTNAME_LENGTH_IS_INVALID)
  @Pattern(regexp = CURRENCY_SHORTNAME_REGEX, message = CURRENCY_SHORTNAME_IS_INVALID)
  private String currencyShortName;

  @Schema(description = TRANSACTION_SUM_DESCRIPTION, example = TRANSACTION_SUM_EXAMPLE)
  @NotNull(message = SUM_IS_NULL)
  @DecimalMin(value = TRANSACTION_SUM_MIN_VALUE, inclusive = false, message = SUM_IS_INVALID)
  private BigDecimal sum;

  @Schema(description = TRANSACTION_EXPENSE_CATEGORY_DESCRIPTION, example = TRANSACTION_EXPENSE_CATEGORY_EXAMPLE)
  @NotBlank(message = EXPENSE_CATEGORY_IS_BLANK)
  @Size(min = EXPENSE_CATEGORY_LENGTH, max = EXPENSE_CATEGORY_LENGTH, message = EXPENSE_CATEGORY_LENGTH_IS_INVALID)
  @Pattern(regexp = EXPENSE_CATEGORY_REGEX, message = EXPENSE_CATEGORY_IS_INVALID)
  private String expenseCategory;

  @Schema(description = TRANSACTION_DATETIME_DESCRIPTION, example = TRANSACTION_DATETIME_EXAMPLE)
  @NotNull(message = DATETIME_IS_NULL)
  private ZonedDateTime dateTime;

  @Schema(description = TRANSACTION_IS_LIMIT_EXCEEDED_DESCRIPTION, example = TRANSACTION_IS_LIMIT_EXCEEDED_EXAMPLE)
  private boolean isLimitExceeded;
}
