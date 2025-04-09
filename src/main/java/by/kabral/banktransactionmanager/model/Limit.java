package by.kabral.banktransactionmanager.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "limits")
@Data
@NoArgsConstructor
public class Limit {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "value")
    private BigDecimal value;

    @Column(name = "datetime")
    private LocalDateTime dateTime;
}
