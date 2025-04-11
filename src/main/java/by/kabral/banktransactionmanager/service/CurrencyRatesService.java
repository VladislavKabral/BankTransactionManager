package by.kabral.banktransactionmanager.service;

import by.kabral.banktransactionmanager.config.prop.ExternalAPIProperties;
import by.kabral.banktransactionmanager.dto.CurrencyRateDto;
import by.kabral.banktransactionmanager.model.CurrencyRate;
import by.kabral.banktransactionmanager.repository.CurrencyRatesRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static by.kabral.banktransactionmanager.util.Currency.*;
import static by.kabral.banktransactionmanager.util.Constant.*;

@Service
@AllArgsConstructor
public class CurrencyRatesService {

    private final ExternalAPIProperties externalAPIProperties;
    private final CurrencyRatesRepository currencyRatesRepository;
    private final RestClient restClient;

    @Scheduled(cron = "@daily")
    @Transactional
    public void loadCurrencyRates() {
        loadCurrencyRate(DOLLAR_SHORT_NAME, TENGE_SHORT_NAME);
        loadCurrencyRate(RUBLE_SHORT_NAME, DOLLAR_SHORT_NAME);
        loadCurrencyRate(EURO_SHORT_NAME, DOLLAR_SHORT_NAME);
    }

    private UriComponents buildCurrencyRatesUri(String symbol) {
        return UriComponentsBuilder.newInstance()
                .scheme(externalAPIProperties.getScheme())
                .host(externalAPIProperties.getHost())
                .path(externalAPIProperties.getPath())
                .queryParam(externalAPIProperties.getSymbolParamName(), symbol)
                .queryParam(externalAPIProperties.getKeyParamName(), externalAPIProperties.getKey())
                .build();
    }

    @Transactional
    protected void loadCurrencyRate(String source, String target) {
        String symbol = getExchangingPair(source, target);
        UriComponents uriComponents = buildCurrencyRatesUri(symbol);

        CurrencyRateDto currencyRateDto = restClient.get()
                .uri(uriComponents.toUri())
                .retrieve()
                .toEntity(CurrencyRateDto.class)
                .getBody();

        CurrencyRate currencyRate = CurrencyRate.builder()
                .source(source)
                .target(target)
                .rate(currencyRateDto.getClose())
                .datetime(ZonedDateTime.now(ZoneId.of(UTC_ZONE_NAME)).toLocalDateTime())
                .build();

        currencyRatesRepository.save(currencyRate);
    }
}
