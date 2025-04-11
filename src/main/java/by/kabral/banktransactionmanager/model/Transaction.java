package by.kabral.banktransactionmanager.model;

import by.kabral.banktransactionmanager.util.ExpenseCategory;
import by.kabral.banktransactionmanager.util.ZonedDateTimeConvertor;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
public class Transaction {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "account_from")
    private String accountFrom;

    @Column(name = "account_to")
    private String accountTo;

    @Column(name = "currency_shortname")
    private String currencyShortname;

    @Column(name = "sum")
    private BigDecimal sum;

    @Column(name = "expense_category")
    private ExpenseCategory expenseCategory;

    @Column(name = "datetime")
    @Convert(converter = ZonedDateTimeConvertor.class)
    private ZonedDateTime datetime;
}
