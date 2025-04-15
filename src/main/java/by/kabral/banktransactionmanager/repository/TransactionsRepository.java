package by.kabral.banktransactionmanager.repository;

import by.kabral.banktransactionmanager.dto.LimitedTransactionDto;
import by.kabral.banktransactionmanager.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionsRepository extends JpaRepository<Transaction, UUID> {

  @Query("""
          select new by.kabral.banktransactionmanager.dto.LimitedTransactionDto(t.id, t.accountFrom, t.accountTo, t.currencyShortname, t.sum,
                             t.expenseCategory, t.datetime, l.value, l.datetime, 'USD')
                             from Transaction t
                             join t.limit l
                             where (l.value - t.sumUsd) < (
                                select sum(tr.sumUsd) from Transaction tr where t.expenseCategory = tr.expenseCategory
                                 and tr.datetime < t.datetime
                                 and date_trunc('month', tr.datetime) = date_trunc('month', t.datetime)
                             )""")
  List<LimitedTransactionDto> findLimitedTransactions();

  @Query("""
          select case
                     when (l.value - t.sumUsd) < (
                         select sum(tr.sumUsd)
                         from Transaction tr
                         where t.expenseCategory = tr.expenseCategory
                           and tr.datetime < t.datetime
                           and date_trunc('month', tr.datetime) = date_trunc('month', t.datetime)
                     )
                     then true
                     else false
                 end as result
          from Transaction t
          join t.limit l
          where t.id = :transactionId
          """)
  Boolean isTransactionLimited(@Param("transactionId") UUID transactionId);
}
