package by.kabral.banktransactionmanager.service;

import by.kabral.banktransactionmanager.dto.LimitedTransactionDto;
import by.kabral.banktransactionmanager.dto.LimitedTransactionListDto;
import by.kabral.banktransactionmanager.dto.TransactionDto;
import by.kabral.banktransactionmanager.dto.TransactionsListDto;
import by.kabral.banktransactionmanager.exception.EntityNotFoundException;
import by.kabral.banktransactionmanager.exception.InvalidRequestDataException;
import by.kabral.banktransactionmanager.mapper.TransactionsMapper;
import by.kabral.banktransactionmanager.model.CurrencyRate;
import by.kabral.banktransactionmanager.model.Transaction;
import by.kabral.banktransactionmanager.repository.TransactionsRepository;
import by.kabral.banktransactionmanager.util.Currency;
import by.kabral.banktransactionmanager.util.validator.TransactionsValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionsServiceImpl implements EntityService<TransactionDto> {

  private final TransactionsRepository transactionsRepository;
  private final CurrencyRatesService currencyRatesService;
  private final LimitsServiceImpl limitsService;
  private final TransactionsMapper transactionsMapper;
  private final TransactionsValidator transactionsValidator;

  @Transactional(readOnly = true)
  public TransactionsListDto findAllTransactions() {
    List<Transaction> transactions = transactionsRepository.findAll();
    List<UUID> limitedTransactionsIds = findLimitedTransactions()
            .getTransactions()
            .stream()
            .map(LimitedTransactionDto::getId)
            .toList();

    List<TransactionDto> transactionsDto = transactionsMapper
            .toDto(transactions)
            .stream()
            .peek(t -> t.setLimitExceeded(limitedTransactionsIds.contains(t.getId())))
            .toList();

    return TransactionsListDto.builder()
            .transactions(transactionsDto)
            .build();
  }

  @Transactional(readOnly = true)
  public LimitedTransactionListDto findLimitedTransactions() {
    return LimitedTransactionListDto.builder()
            .transactions(transactionsRepository.findLimitedTransactions())
            .build();
  }

  @Transactional
  public TransactionDto save(TransactionDto transactionDto) throws InvalidRequestDataException, EntityNotFoundException {
    Map<String, CurrencyRate> currentRates = currencyRatesService.getCurrentRates();

    transactionDto.setExpenseCategory(transactionDto.getExpenseCategory().toUpperCase());
    transactionDto.setCurrencyShortName(transactionDto.getCurrencyShortName().toUpperCase());
    transactionsValidator.validate(transactionDto);


    Transaction transaction = transactionsMapper.toEntity(transactionDto);
    transaction.setLimit(limitsService.findCurrentLimit(transaction.getExpenseCategory()));

    if (transaction.getCurrencyShortname().equals(Currency.DOLLAR_SHORT_NAME)) {
      transaction.setSumUsd(transaction.getSum());
    } else {
      transaction.setSumUsd(transaction.getSum().divide(
              currentRates.get(transaction.getCurrencyShortname()).getRate(),
              Currency.SCALE,
              RoundingMode.HALF_UP
      ));
    }

    TransactionDto result = transactionsMapper.toDto(transactionsRepository.save(transaction));
    result.setLimitExceeded(transactionsRepository.isTransactionLimited(result.getId()));

    return result;
  }
}
