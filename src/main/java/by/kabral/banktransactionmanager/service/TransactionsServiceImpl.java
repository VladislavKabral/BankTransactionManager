package by.kabral.banktransactionmanager.service;

import by.kabral.banktransactionmanager.dto.LimitedTransactionDto;
import by.kabral.banktransactionmanager.dto.LimitedTransactionListDto;
import by.kabral.banktransactionmanager.dto.TransactionDto;
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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TransactionsServiceImpl implements EntityService<TransactionDto> {

  private final TransactionsRepository transactionsRepository;
  private final CurrencyRatesService currencyRatesService;
  private final TransactionsMapper transactionsMapper;
  private final TransactionsValidator transactionsValidator;

  @Transactional(readOnly = true)
  public LimitedTransactionListDto findLimitedTransactions() throws EntityNotFoundException {
    Map<String, CurrencyRate> currentRates = currencyRatesService.getCurrentRates();
    List<LimitedTransactionDto> transactions = transactionsRepository.findLimitedTransactions();

    List<LimitedTransactionDto> filteredTransactions = transactions.stream()
            .filter(t -> !isTransactionLimited(t, transactions, currentRates))
            .toList();

    return LimitedTransactionListDto.builder()
            .transactions(filteredTransactions)
            .build();
  }

  @Transactional
  public TransactionDto save(TransactionDto transactionDto) throws InvalidRequestDataException {
    transactionDto.setExpenseCategory(transactionDto.getExpenseCategory().toUpperCase());
    transactionDto.setCurrencyShortName(transactionDto.getCurrencyShortName().toUpperCase());
    transactionsValidator.validate(transactionDto);

    Transaction transaction = transactionsMapper.toEntity(transactionDto);

    return transactionsMapper.toDto(transactionsRepository.save(transaction));
  }

  private boolean isTransactionLimited(LimitedTransactionDto transaction,
                                       List<LimitedTransactionDto> transactions,
                                       Map<String, CurrencyRate> currentRates) {
    BigDecimal sum = BigDecimal.ZERO;

    List<LimitedTransactionDto> filteredTransactions = transactions.stream()
            .filter(t -> t.getDateTime().isBefore(transaction.getDateTime()) && !t.getId().equals(transaction.getId()))
            .toList();

    for (LimitedTransactionDto t : filteredTransactions) {
      if (!t.getCurrencyShortName().equals(Currency.DOLLAR_SHORT_NAME)) {
        sum = sum.add(t.getSum().divide(
                currentRates.get(t.getCurrencyShortName()).getRate(),
                Currency.SCALE,
                RoundingMode.HALF_UP)
        );
      } else {
        sum = sum.add(t.getSum());
      }
    }

    BigDecimal difference;
    if (!transaction.getCurrencyShortName().equals(Currency.DOLLAR_SHORT_NAME)) {
      difference = transaction.getLimitSum().subtract(transaction.getSum().divide(
                      currentRates.get(transaction.getCurrencyShortName()).getRate(),
                      Currency.SCALE,
                      RoundingMode.HALF_UP
              ));
    } else {
      difference = transaction.getLimitSum().subtract(transaction.getSum());
    }

    return difference.compareTo(sum) >= 0;
  }
}
