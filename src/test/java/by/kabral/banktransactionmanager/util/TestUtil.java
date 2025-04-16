package by.kabral.banktransactionmanager.util;

import by.kabral.banktransactionmanager.dto.LimitDto;
import by.kabral.banktransactionmanager.dto.LimitedTransactionDto;
import by.kabral.banktransactionmanager.dto.TransactionDto;
import by.kabral.banktransactionmanager.model.Limit;
import by.kabral.banktransactionmanager.model.Transaction;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@UtilityClass
public class TestUtil {

  public final UUID FIRST_CASE_FIRST_ID = UUID.fromString("644aafe1-36ef-4163-b331-b42d2f6d57f7");
  public final UUID FIRST_CASE_SECOND_ID = UUID.fromString("8b629714-0628-403c-ac92-30fb30e1b26c");
  public final UUID SECOND_CASE_FIRST_ID = UUID.fromString("644aafe1-36ef-4163-b331-b42d2f6d57f7");
  public final UUID SECOND_CASE_SECOND_ID = UUID.fromString("3273bf02-d3aa-4f97-94f6-4ee989bf5bf6");
  public final BigDecimal DEFAULT_LIMIT_VALUE = BigDecimal.valueOf(1000);
  public final UUID LIMIT_FIRST_ID = UUID.fromString("1a2b8814-5e1d-4f27-bb14-20c32d3be7c5");
  public final UUID LIMIT_SECOND_ID = UUID.fromString("212e7e38-c4fb-4418-bd85-5685d61d33f7");
  public final String TRANSACTION_DEFAULT_ACCOUNT_FROM = "1111111111";
  public final String TRANSACTION_DEFAULT_ACCOUNT_TO = "9999999999";
  public final UUID TRANSACTION_DEFAULT_ID = UUID.fromString("f91d05bd-0ebd-499a-9b9d-2f33a4bf7e0b");
  public final BigDecimal TRANSACTION_DEFAULT_SUM = BigDecimal.valueOf(500);
  public final String INVALID_EXPENSE_CATEGORY_NAME = "PR4DUCT";
  public final boolean TRANSACTION_DEFAULT_LIMIT_EXCEEDED_VALUE = false;

  public Limit getLimit() {
    return Limit.builder()
            .id(LIMIT_FIRST_ID)
            .value(DEFAULT_LIMIT_VALUE)
            .type(ExpenseCategory.PRODUCT)
            .datetime(ZonedDateTime.now(ZoneId.of(Constant.UTC_ZONE_NAME)))
            .build();
  }

  public List<Limit> getLimits() {
    return List.of(
            Limit.builder()
                    .id(LIMIT_FIRST_ID)
                    .value(DEFAULT_LIMIT_VALUE)
                    .type(ExpenseCategory.PRODUCT)
                    .datetime(ZonedDateTime.now(ZoneId.of(Constant.UTC_ZONE_NAME)))
                    .build(),
            Limit.builder()
                    .id(LIMIT_SECOND_ID)
                    .value(DEFAULT_LIMIT_VALUE)
                    .type(ExpenseCategory.SERVICE)
                    .datetime(ZonedDateTime.now(ZoneId.of(Constant.UTC_ZONE_NAME)))
                    .build()
    );
  }

  public List<Transaction> getEmptyListOfTransactions() {
    return new ArrayList<>();
  }

  public List<TransactionDto> getEmptyListOfTransactionDtos() {
    return new ArrayList<>();
  }

  public List<Transaction> getTransactions() {
    return List.of(
            Transaction.builder()
                    .id(TRANSACTION_DEFAULT_ID)
                    .accountFrom(TRANSACTION_DEFAULT_ACCOUNT_FROM)
                    .accountTo(TRANSACTION_DEFAULT_ACCOUNT_TO)
                    .currencyShortname(Currency.DOLLAR_SHORT_NAME)
                    .limit(getLimit())
                    .sum(TRANSACTION_DEFAULT_SUM)
                    .sumUsd(TRANSACTION_DEFAULT_SUM)
                    .datetime(ZonedDateTime.now(ZoneId.of(Constant.UTC_ZONE_NAME)))
                    .expenseCategory(ExpenseCategory.SERVICE)
                    .build()
    );
  }

  public LimitDto getRequestForNewLimit() {
    return LimitDto.builder()
            .type(ExpenseCategory.PRODUCT.name())
            .value(DEFAULT_LIMIT_VALUE)
            .build();
  }

  public LimitDto getInvalidRequestForNewLimit() {
    return LimitDto.builder()
            .type(INVALID_EXPENSE_CATEGORY_NAME)
            .value(DEFAULT_LIMIT_VALUE)
            .build();
  }

  public LimitDto getLimitDto() {
    return LimitDto.builder()
            .type(ExpenseCategory.PRODUCT.name())
            .value(DEFAULT_LIMIT_VALUE)
            .dateTime(ZonedDateTime.now(ZoneId.of(Constant.UTC_ZONE_NAME)))
            .build();
  }

  public List<TransactionDto> getEmptyTransactionsDto() {
    return new ArrayList<>();
  }

  public TransactionDto getTransactionDto() {
    return TransactionDto.builder()
            .id(TRANSACTION_DEFAULT_ID)
            .accountFrom(TRANSACTION_DEFAULT_ACCOUNT_FROM)
            .accountTo(TRANSACTION_DEFAULT_ACCOUNT_TO)
            .sum(TRANSACTION_DEFAULT_SUM)
            .currencyShortName(Currency.DOLLAR_SHORT_NAME)
            .dateTime(ZonedDateTime.now(ZoneId.of(Constant.UTC_ZONE_NAME)))
            .expenseCategory(ExpenseCategory.PRODUCT.name())
            .isLimitExceeded(TRANSACTION_DEFAULT_LIMIT_EXCEEDED_VALUE)
            .build();
  }

  public TransactionDto getInValidRequestForNewTransaction() {
    return TransactionDto.builder()
            .accountFrom("1111111l11")
            .accountTo(TRANSACTION_DEFAULT_ACCOUNT_TO)
            .currencyShortName(Currency.DOLLAR_SHORT_NAME)
            .sum(TRANSACTION_DEFAULT_SUM)
            .expenseCategory(ExpenseCategory.PRODUCT.name())
            .dateTime(ZonedDateTime.now(ZoneId.of(Constant.UTC_ZONE_NAME)))
            .build();
  }

  public Transaction getFilledTransaction() {
    return Transaction.builder()
            .id(TRANSACTION_DEFAULT_ID)
            .accountFrom(TRANSACTION_DEFAULT_ACCOUNT_FROM)
            .accountTo(TRANSACTION_DEFAULT_ACCOUNT_TO)
            .currencyShortname(Currency.DOLLAR_SHORT_NAME)
            .sum(TRANSACTION_DEFAULT_SUM)
            .expenseCategory(ExpenseCategory.PRODUCT)
            .datetime(ZonedDateTime.now(ZoneId.of(Constant.UTC_ZONE_NAME)))
            .sumUsd(TRANSACTION_DEFAULT_SUM)
            .limit(getLimit())
            .build();
  }

  public TransactionDto getFilledTransactionDto() {
    return TransactionDto.builder()
            .id(TRANSACTION_DEFAULT_ID)
            .accountFrom(TRANSACTION_DEFAULT_ACCOUNT_FROM)
            .accountTo(TRANSACTION_DEFAULT_ACCOUNT_TO)
            .currencyShortName(Currency.DOLLAR_SHORT_NAME)
            .sum(TRANSACTION_DEFAULT_SUM)
            .expenseCategory(ExpenseCategory.PRODUCT.name())
            .dateTime(ZonedDateTime.now(ZoneId.of(Constant.UTC_ZONE_NAME)))
            .isLimitExceeded(TRANSACTION_DEFAULT_LIMIT_EXCEEDED_VALUE)
            .build();
  }

  public Transaction getTransaction() {
    return Transaction.builder()
            .accountFrom(TRANSACTION_DEFAULT_ACCOUNT_FROM)
            .accountTo(TRANSACTION_DEFAULT_ACCOUNT_TO)
            .currencyShortname(Currency.DOLLAR_SHORT_NAME)
            .sum(TRANSACTION_DEFAULT_SUM)
            .expenseCategory(ExpenseCategory.PRODUCT)
            .datetime(ZonedDateTime.now(ZoneId.of(Constant.UTC_ZONE_NAME)))
            .build();
  }

  public TransactionDto getRequestForNewTransaction() {
    return TransactionDto.builder()
            .accountFrom(TRANSACTION_DEFAULT_ACCOUNT_FROM)
            .accountTo(TRANSACTION_DEFAULT_ACCOUNT_TO)
            .currencyShortName(Currency.DOLLAR_SHORT_NAME)
            .sum(TRANSACTION_DEFAULT_SUM)
            .expenseCategory(ExpenseCategory.PRODUCT.name())
            .dateTime(ZonedDateTime.now(ZoneId.of(Constant.UTC_ZONE_NAME)))
            .build();
  }

  public List<LimitedTransactionDto> getLimitedTransactionsDto() {
    return List.of(
            LimitedTransactionDto.builder()
                    .id(TRANSACTION_DEFAULT_ID)
                    .accountFrom(TRANSACTION_DEFAULT_ACCOUNT_FROM)
                    .accountTo(TRANSACTION_DEFAULT_ACCOUNT_TO)
                    .sum(TRANSACTION_DEFAULT_SUM)
                    .currencyShortName(Currency.DOLLAR_SHORT_NAME)
                    .dateTime(ZonedDateTime.now(ZoneId.of(Constant.UTC_ZONE_NAME)))
                    .expenseCategory(ExpenseCategory.PRODUCT)
                    .limitSum(DEFAULT_LIMIT_VALUE)
                    .limitCurrencyShortname(Currency.DOLLAR_SHORT_NAME)
                    .limitDatetime(ZonedDateTime.now(ZoneId.of(Constant.UTC_ZONE_NAME)))
                    .build()
    );
  }

  public List<TransactionDto> getTransactionsDto() {
    return List.of(
            TransactionDto.builder()
                    .id(TRANSACTION_DEFAULT_ID)
                    .accountFrom(TRANSACTION_DEFAULT_ACCOUNT_FROM)
                    .accountTo(TRANSACTION_DEFAULT_ACCOUNT_TO)
                    .sum(TRANSACTION_DEFAULT_SUM)
                    .currencyShortName(Currency.DOLLAR_SHORT_NAME)
                    .dateTime(ZonedDateTime.now(ZoneId.of(Constant.UTC_ZONE_NAME)))
                    .expenseCategory(ExpenseCategory.PRODUCT.name())
                    .isLimitExceeded(TRANSACTION_DEFAULT_LIMIT_EXCEEDED_VALUE)
                    .build()
    );
  }

  public List<LimitDto> getEmptyLimitsDto() {
    return new ArrayList<>();
  }

  public List<LimitDto> getLimitsDto() {
    return List.of(
            LimitDto.builder()
                    .type(ExpenseCategory.PRODUCT.name())
                    .value(DEFAULT_LIMIT_VALUE)
                    .dateTime(ZonedDateTime.now(ZoneId.of(Constant.UTC_ZONE_NAME)))
                    .build(),
            LimitDto.builder()
                    .type(ExpenseCategory.SERVICE.name())
                    .value(DEFAULT_LIMIT_VALUE)
                    .dateTime(ZonedDateTime.now(ZoneId.of(Constant.UTC_ZONE_NAME)))
                    .build()
    );
  }
}
