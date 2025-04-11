package by.kabral.banktransactionmanager.model;

import by.kabral.banktransactionmanager.util.ZonedDateTimeConvertor;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
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

    @Column(name = "type")
    private String type;

    @Column(name = "value")
    private BigDecimal value;

    @Column(name = "datetime")
    @Convert(converter = ZonedDateTimeConvertor.class)
    private ZonedDateTime dateTime;
}
