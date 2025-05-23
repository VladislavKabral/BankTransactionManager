package by.kabral.banktransactionmanager.mapper;

import by.kabral.banktransactionmanager.dto.TransactionDto;
import by.kabral.banktransactionmanager.model.Transaction;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TransactionsMapper {

  private final ModelMapper modelMapper;

  public Transaction toEntity(TransactionDto transactionDto) {
    return modelMapper.map(transactionDto, Transaction.class);
  }

  public TransactionDto toDto(Transaction transaction) {
    return modelMapper.map(transaction, TransactionDto.class);
  }

  public List<TransactionDto> toDto(List<Transaction> transactions) {
    return transactions.stream()
            .map(this::toDto)
            .toList();
  }
}
