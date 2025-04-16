package by.kabral.banktransactionmanager.service;

import by.kabral.banktransactionmanager.dto.LimitedTransactionDto;
import by.kabral.banktransactionmanager.dto.LimitedTransactionListDto;
import by.kabral.banktransactionmanager.dto.TransactionDto;
import by.kabral.banktransactionmanager.dto.TransactionsListDto;
import by.kabral.banktransactionmanager.exception.EntityNotFoundException;
import by.kabral.banktransactionmanager.exception.InvalidRequestDataException;
import by.kabral.banktransactionmanager.mapper.TransactionsMapper;
import by.kabral.banktransactionmanager.model.Transaction;
import by.kabral.banktransactionmanager.repository.TransactionsRepository;
import by.kabral.banktransactionmanager.util.Currency;
import by.kabral.banktransactionmanager.util.validator.TransactionsValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static by.kabral.banktransactionmanager.util.Message.EXPENSE_CATEGORY_IS_UNKNOWN;
import static by.kabral.banktransactionmanager.util.TestUtil.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransactionsServiceTest {

  @InjectMocks
  private TransactionsServiceImpl transactionsService;

  @Mock
  private TransactionsRepository transactionsRepository;

  @Mock
  private TransactionsMapper transactionsMapper;

  @Mock
  private CurrencyRatesService currencyRatesService;

  @Mock
  private TransactionsValidator transactionsValidator;

  @Mock
  private LimitsServiceImpl limitsService;

  @Test
  public void testFindAllTransactionsWhenTransactionsExistReturnListOfTransactions() {
    //given
    List<Transaction> transactions = getTransactions();
    List<TransactionDto> transactionDtos = getTransactionsDto();

    //when
    when(transactionsRepository.findAll()).thenReturn(transactions);
    when(transactionsRepository.findLimitedTransactions()).thenReturn(new ArrayList<>());
    when(transactionsMapper.toDto(transactions)).thenReturn(transactionDtos);

    //then
    TransactionsListDto result = transactionsService.findAllTransactions();
    assertThat(result.getTransactions().size()).isEqualTo(1);
    assertThat(result.getTransactions().get(0).getId()).isEqualTo(TRANSACTION_DEFAULT_ID);
    assertThat(result.getTransactions().get(0).getCurrencyShortName()).isEqualTo(Currency.DOLLAR_SHORT_NAME);
    assertThat(result.getTransactions().get(0).getSum()).isEqualTo(TRANSACTION_DEFAULT_SUM);
  }

  @Test
  public void testFindAllTransactionsWhenTransactionsDoNotExistReturnEmptyListOfTransactions() {
    //given
    List<Transaction> transactions = getEmptyListOfTransactions();
    List<TransactionDto> transactionDtos = getEmptyListOfTransactionDtos();

    //when
    when(transactionsRepository.findAll()).thenReturn(getEmptyListOfTransactions());
    when(transactionsRepository.findLimitedTransactions()).thenReturn(new ArrayList<>());
    when(transactionsMapper.toDto(transactions)).thenReturn(transactionDtos);

    //then
    TransactionsListDto result = transactionsService.findAllTransactions();
    assertThat(result.getTransactions().size()).isEqualTo(0);
  }

  @Test
  public void testFindLimitedTransactionsWhenTransactionsExistReturnListOfTransactions() {
    //given
    List<LimitedTransactionDto> transactions = getLimitedTransactionsDto();

    //when
    when(transactionsRepository.findLimitedTransactions()).thenReturn(transactions);

    //then
    LimitedTransactionListDto result = transactionsService.findLimitedTransactions();
    assertThat(result.getTransactions().size()).isEqualTo(1);
    assertThat(result.getTransactions().get(0).getId()).isEqualTo(TRANSACTION_DEFAULT_ID);
    assertThat(result.getTransactions().get(0).getCurrencyShortName()).isEqualTo(Currency.DOLLAR_SHORT_NAME);
    assertThat(result.getTransactions().get(0).getSum()).isEqualTo(TRANSACTION_DEFAULT_SUM);
    assertThat(result.getTransactions().get(0).getLimitSum()).isEqualTo(DEFAULT_LIMIT_VALUE);
  }

  @Test
  public void testSaveTransactionWhenTheRequestIsValidReturnNewTransaction() throws InvalidRequestDataException, EntityNotFoundException {
    //given
    TransactionDto request = getRequestForNewTransaction();
    Transaction transaction = getTransaction();
    Transaction savedTransaction = getFilledTransaction();
    TransactionDto savedTransactionDto = getFilledTransactionDto();

    //when
    when(transactionsMapper.toEntity(request)).thenReturn(transaction);
    when(transactionsRepository.save(transaction)).thenReturn(savedTransaction);
    when(transactionsMapper.toDto(savedTransaction)).thenReturn(savedTransactionDto);

    //then
    TransactionDto result = transactionsService.save(request);
    assertThat(result.getId()).isEqualTo(TRANSACTION_DEFAULT_ID);
    assertThat(result.getCurrencyShortName()).isEqualTo(Currency.DOLLAR_SHORT_NAME);
    assertThat(result.getSum()).isEqualTo(TRANSACTION_DEFAULT_SUM);
    assertFalse(result.isLimitExceeded());
  }

  @Test
  public void testSaveTransactionWhenTheRequestIsInvalidReturnException() throws InvalidRequestDataException {
    //given
    TransactionDto request = getInValidRequestForNewTransaction();

    //when
    doThrow(new InvalidRequestDataException(EXPENSE_CATEGORY_IS_UNKNOWN)).when(transactionsValidator).validate(request);

    //then
    InvalidRequestDataException exception = assertThrows(InvalidRequestDataException.class,
            () -> transactionsService.save(request));
    assertEquals(EXPENSE_CATEGORY_IS_UNKNOWN, exception.getMessage());
  }
}
