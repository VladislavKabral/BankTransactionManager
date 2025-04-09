package by.kabral.banktransactionmanager.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "currency_rates")
@Data
@NoArgsConstructor
public class CurrencyRate {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "source")
    private String source;

    @Column(name = "target")
    private String target;

    @Column(name = "rate")
    private BigDecimal rate;

    @Column(name = "datetime")
    private LocalDateTime datetime;
}
