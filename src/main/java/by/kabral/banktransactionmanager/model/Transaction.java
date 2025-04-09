package by.kabral.banktransactionmanager.model;

import by.kabral.banktransactionmanager.util.ExpenseCategory;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
    private long accountFrom;

    @Column(name = "account_to")
    private long accountTo;

    @Column(name = "currency_shortname")
    private String currencyShortName;

    @Column(name = "sum")
    private BigDecimal sum;

    @Column(name = "expense_category")
    private ExpenseCategory expenseCategory;

    @Column(name = "datetime")
    private LocalDateTime datetime;
}
