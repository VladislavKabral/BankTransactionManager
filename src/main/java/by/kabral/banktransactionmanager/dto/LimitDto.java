package by.kabral.banktransactionmanager.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Data
public class LimitDto {

  private String type;
  private BigDecimal value;
  private ZonedDateTime dateTime;
}
