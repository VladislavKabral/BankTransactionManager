package by.kabral.banktransactionmanager.service;

import by.kabral.banktransactionmanager.dto.TransactionDto;
import by.kabral.banktransactionmanager.exception.InvalidRequestDataException;
import by.kabral.banktransactionmanager.mapper.TransactionsMapper;
import by.kabral.banktransactionmanager.model.Transaction;
import by.kabral.banktransactionmanager.repository.TransactionsRepository;
import by.kabral.banktransactionmanager.util.validator.TransactionsValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TransactionsService implements EntityService<TransactionDto> {

  private final TransactionsRepository transactionsRepository;
  private final TransactionsMapper transactionsMapper;
  private final TransactionsValidator transactionsValidator;

  @Transactional
  public TransactionDto save(TransactionDto transactionDto) throws InvalidRequestDataException {
    transactionDto.setExpenseCategory(transactionDto.getExpenseCategory().toUpperCase());
    transactionDto.setCurrencyShortName(transactionDto.getCurrencyShortName().toUpperCase());
    transactionsValidator.validate(transactionDto);

    Transaction transaction = transactionsMapper.toEntity(transactionDto);

    return transactionsMapper.toDto(transactionsRepository.save(transaction));
  }
}
