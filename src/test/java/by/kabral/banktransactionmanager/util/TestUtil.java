package by.kabral.banktransactionmanager.util;

import by.kabral.banktransactionmanager.dto.LimitDto;
import by.kabral.banktransactionmanager.dto.LimitedTransactionDto;
import by.kabral.banktransactionmanager.dto.TransactionDto;
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

  public LimitDto getRequestForNewLimit() {
    return LimitDto.builder()
            .type(ExpenseCategory.PRODUCT.name())
            .value(DEFAULT_LIMIT_VALUE)
            .build();
  }

  public LimitDto getInvalidRequestForNewLimit() {
    return LimitDto.builder()
            .type("PR4DUCT")
            .value(DEFAULT_LIMIT_VALUE)
            .build();
  }

  public LimitDto getLimit() {
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
            .id(UUID.fromString("644aafe1-36ef-4163-b331-b42d2f6d57f7"))
            .accountFrom("1111111111")
            .accountTo("9999999999")
            .sum(BigDecimal.valueOf(1000))
            .currencyShortName("USD")
            .dateTime(ZonedDateTime.now(ZoneId.of(Constant.UTC_ZONE_NAME)))
            .expenseCategory(ExpenseCategory.PRODUCT.name())
            .isLimitExceeded(false)
            .build();
  }

  public TransactionDto getInValidRequestForNewTransaction() {
    return TransactionDto.builder()
            .accountFrom("1111111111")
            .accountTo("9999999f99")
            .currencyShortName("USD")
            .sum(BigDecimal.valueOf(1000))
            .expenseCategory(ExpenseCategory.PRODUCT.name())
            .dateTime(ZonedDateTime.now(ZoneId.of(Constant.UTC_ZONE_NAME)))
            .build();
  }

  public TransactionDto getRequestForNewTransaction() {
    return TransactionDto.builder()
            .accountFrom("1111111111")
            .accountTo("9999999999")
            .currencyShortName("USD")
            .sum(BigDecimal.valueOf(1000))
            .expenseCategory(ExpenseCategory.PRODUCT.name())
            .dateTime(ZonedDateTime.now(ZoneId.of(Constant.UTC_ZONE_NAME)))
            .build();
  }

  public List<LimitedTransactionDto> getLimitedTransactionsDto() {
    return List.of(
            LimitedTransactionDto.builder()
                    .id(UUID.fromString("644aafe1-36ef-4163-b331-b42d2f6d57f7"))
                    .accountFrom("1111111111")
                    .accountTo("9999999999")
                    .sum(BigDecimal.valueOf(1000))
                    .currencyShortName("USD")
                    .dateTime(ZonedDateTime.now(ZoneId.of(Constant.UTC_ZONE_NAME)))
                    .expenseCategory(ExpenseCategory.PRODUCT)
                    .limitSum(BigDecimal.valueOf(2000))
                    .limitCurrencyShortname("USD")
                    .limitDatetime(ZonedDateTime.now(ZoneId.of(Constant.UTC_ZONE_NAME)))
                    .build()
    );
  }

  public List<TransactionDto> getTransactionsDto() {
    return List.of(
            TransactionDto.builder()
                    .id(UUID.fromString("644aafe1-36ef-4163-b331-b42d2f6d57f7"))
                    .accountFrom("1111111111")
                    .accountTo("9999999999")
                    .sum(BigDecimal.valueOf(500))
                    .currencyShortName("USD")
                    .dateTime(ZonedDateTime.now(ZoneId.of(Constant.UTC_ZONE_NAME)))
                    .expenseCategory(ExpenseCategory.PRODUCT.name())
                    .isLimitExceeded(false)
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
                    .type(ExpenseCategory.PRODUCT.name())
                    .value(DEFAULT_LIMIT_VALUE)
                    .dateTime(ZonedDateTime.now(ZoneId.of(Constant.UTC_ZONE_NAME)))
                    .build()
    );
  }
}
