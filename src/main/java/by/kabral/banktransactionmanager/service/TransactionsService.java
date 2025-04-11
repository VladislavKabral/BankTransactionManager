package by.kabral.banktransactionmanager.service;

import by.kabral.banktransactionmanager.dto.TransactionDto;
import by.kabral.banktransactionmanager.mapper.TransactionsMapper;
import by.kabral.banktransactionmanager.model.Transaction;
import by.kabral.banktransactionmanager.repository.TransactionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TransactionsService {

  private final TransactionsRepository transactionsRepository;
  private final TransactionsMapper transactionsMapper;

  @Transactional
  public TransactionDto save(TransactionDto transactionDto) {
    Transaction transaction = transactionsMapper.toEntity(transactionDto);

    return transactionsMapper.toDto(transactionsRepository.save(transaction));
  }
}
