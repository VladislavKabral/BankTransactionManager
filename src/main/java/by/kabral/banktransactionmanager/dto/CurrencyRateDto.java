package by.kabral.banktransactionmanager.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CurrencyRateDto {

    private String symbol;
    private BigDecimal close;
}
