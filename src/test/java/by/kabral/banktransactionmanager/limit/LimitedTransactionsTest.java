package by.kabral.banktransactionmanager.limit;

import by.kabral.banktransactionmanager.dto.LimitedTransactionDto;
import by.kabral.banktransactionmanager.dto.LimitedTransactionListDto;
import by.kabral.banktransactionmanager.service.TransactionsServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.UUID;

import static by.kabral.banktransactionmanager.util.TestUtil.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestPropertySource("/application-test.properties")
public class LimitedTransactionsTest extends BaseIntegrationTest {

  @Autowired
  private TransactionsServiceImpl transactionsService;

  @Test
  @Sql({"classpath:db/reset.sql", "classpath:db/firstCase.sql"})
  void testTheFirstCase() {
    LimitedTransactionListDto transactionList = transactionsService.findLimitedTransactions();
    List<UUID> ids = transactionList
            .getTransactions()
            .stream()
            .map(LimitedTransactionDto::getId)
            .toList();
    assertThat(transactionList.getTransactions().size()).isEqualTo(2);
    assertTrue(ids.contains(FIRST_CASE_FIRST_ID));
    assertTrue(ids.contains(FIRST_CASE_SECOND_ID));
  }

  @Test
  @Sql({"classpath:db/reset.sql", "classpath:db/secondCase.sql"})
  void testTheSecondCase() {
    LimitedTransactionListDto transactionList = transactionsService.findLimitedTransactions();
    List<UUID> ids = transactionList
            .getTransactions()
            .stream()
            .map(LimitedTransactionDto::getId)
            .toList();
    assertThat(transactionList.getTransactions().size()).isEqualTo(2);
    assertTrue(ids.contains(SECOND_CASE_FIRST_ID));
    assertTrue(ids.contains(SECOND_CASE_SECOND_ID));
  }
}
