package by.kabral.banktransactionmanager.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

import static by.kabral.banktransactionmanager.util.Description.*;

@Schema(description = TRANSACTIONS_LIST_DESCRIPTION)
@Data
@AllArgsConstructor
@Builder
public class TransactionsListDto {

  @Schema(description = TRANSACTIONS_LIST_VALUE_DESCRIPTION)
  private List<TransactionDto> transactions;
}
