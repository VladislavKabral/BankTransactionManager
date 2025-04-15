package by.kabral.banktransactionmanager.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static by.kabral.banktransactionmanager.util.Description.*;

@Schema(description = LIMITS_DESCRIPTION)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LimitsListDto {

  @Schema(description = LIMITS_VALUE_DESCRIPTION)
  private List<LimitDto> limits;
}
