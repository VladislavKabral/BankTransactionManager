package by.kabral.banktransactionmanager.repository;

import by.kabral.banktransactionmanager.dto.LimitedTransactionDto;
import by.kabral.banktransactionmanager.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionsRepository extends JpaRepository<Transaction, UUID> {

  @Query("""
          select new by.kabral.banktransactionmanager.dto.LimitedTransactionDto(
              t.id,
              t.accountFrom,
              t.accountTo,
              t.currencyShortname,
              t.sum,
              t.expenseCategory,
              t.datetime,
              (select l.value from Limit l where
                                            l.datetime = (select lm.datetime from Limit lm
                                                                                      where t.datetime > lm.datetime
                                                                                      order by lm.datetime desc
                                                                                      limit 1)
                                        and l.type = t.expenseCategory
              ),
              (select l.datetime from Limit l where
                  l.datetime = (select lm.datetime from Limit lm
                                     where t.datetime > lm.datetime
                                     order by lm.datetime desc
                                     limit 1)
                                          and l.type = t.expenseCategory
              ),
              'USD')
              from Transaction t""")
  List<LimitedTransactionDto> findLimitedTransactions();
}
