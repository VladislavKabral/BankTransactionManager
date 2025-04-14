package by.kabral.banktransactionmanager.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import static by.kabral.banktransactionmanager.util.Constant.*;
import static by.kabral.banktransactionmanager.util.Message.*;
import static by.kabral.banktransactionmanager.util.Regex.*;

@Data
@Builder
public class LimitDto {

  @NotBlank(message = LIMIT_TYPE_IS_BLANK)
  @Size(min = LIMIT_TYPE_LENGTH, max = LIMIT_TYPE_LENGTH, message = LIMIT_TYPE_LENGTH_IS_INVALID)
  @Pattern(regexp = LIMIT_TYPE_REGEX, message = LIMIT_TYPE_IS_INVALID)
  private String type;

  @NotNull(message = LIMIT_VALUE_IS_NULL)
  @DecimalMin(value = LIMIT_VALUE_MIN_VALUE, message = LIMIT_VALUE_IS_INVALID)
  private BigDecimal value;

  private ZonedDateTime dateTime;
}
