package by.kabral.banktransactionmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class LimitedTransactionListDto {

  private List<LimitedTransactionDto> transactions;
}
