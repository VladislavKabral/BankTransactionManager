package by.kabral.banktransactionmanager.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

import static by.kabral.banktransactionmanager.util.Description.*;

@Schema(description = LIMITED_TRANSACTIONS_DESCRIPTION)
@Data
@AllArgsConstructor
@Builder
public class LimitedTransactionListDto {

  @Schema(description = LIMITED_TRANSACTIONS_VALUE_DESCRIPTION)
  private List<LimitedTransactionDto> transactions;
}
