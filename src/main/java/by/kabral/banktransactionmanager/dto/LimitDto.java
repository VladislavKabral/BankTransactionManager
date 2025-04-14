package by.kabral.banktransactionmanager.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import static by.kabral.banktransactionmanager.util.Constant.*;
import static by.kabral.banktransactionmanager.util.Description.*;
import static by.kabral.banktransactionmanager.util.Message.*;
import static by.kabral.banktransactionmanager.util.Regex.*;

@Schema(description = LIMIT_DESCRIPTION)
@Data
@Builder
public class LimitDto {

  @Schema(description = LIMIT_TYPE_DESCRIPTION, example = LIMIT_TYPE_EXAMPLE)
  @NotBlank(message = LIMIT_TYPE_IS_BLANK)
  @Size(min = LIMIT_TYPE_LENGTH, max = LIMIT_TYPE_LENGTH, message = LIMIT_TYPE_LENGTH_IS_INVALID)
  @Pattern(regexp = LIMIT_TYPE_REGEX, message = LIMIT_TYPE_IS_INVALID)
  private String type;

  @Schema(description = LIMIT_VALUE_DESCRIPTION, example = LIMIT_VALUE_EXAMPLE)
  @NotNull(message = LIMIT_VALUE_IS_NULL)
  @DecimalMin(value = LIMIT_VALUE_MIN_VALUE, message = LIMIT_VALUE_IS_INVALID)
  private BigDecimal value;

  @Schema(description = LIMIT_DATETIME_DESCRIPTION, example = LIMIT_DATETIME_EXAMPLE)
  private ZonedDateTime dateTime;
}
