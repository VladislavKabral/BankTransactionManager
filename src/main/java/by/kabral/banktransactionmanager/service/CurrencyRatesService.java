package by.kabral.banktransactionmanager.service;

import by.kabral.banktransactionmanager.config.prop.ExternalAPIProperties;
import by.kabral.banktransactionmanager.dto.CurrencyRateDto;
import by.kabral.banktransactionmanager.exception.EntityNotFoundException;
import by.kabral.banktransactionmanager.model.CurrencyRate;
import by.kabral.banktransactionmanager.repository.CurrencyRatesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Map;

import static by.kabral.banktransactionmanager.util.Currency.*;
import static by.kabral.banktransactionmanager.util.Constant.*;
import static by.kabral.banktransactionmanager.util.LogMessage.*;
import static by.kabral.banktransactionmanager.util.Message.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class CurrencyRatesService {

    private final ExternalAPIProperties externalAPIProperties;
    private final CurrencyRatesRepository currencyRatesRepository;
    private final RestClient restClient;

    @Transactional(readOnly = true)
    public CurrencyRate getCurrentRateByTargetShortname(String shortName) throws EntityNotFoundException {
        return currencyRatesRepository.findFirstByTargetOrderByDatetimeDesc(shortName)
                .orElseThrow(() -> new EntityNotFoundException(String.format(CURRENCY_RATE_IS_NOT_FOUND, shortName)));
    }

    @Transactional(readOnly = true)
    public Map<String, CurrencyRate> getCurrentRates() throws EntityNotFoundException {
        return Map.of(
                TENGE_SHORT_NAME, getCurrentRateByTargetShortname(TENGE_SHORT_NAME),
                EURO_SHORT_NAME, getCurrentRateByTargetShortname(EURO_SHORT_NAME),
                RUBLE_SHORT_NAME, getCurrentRateByTargetShortname(RUBLE_SHORT_NAME)
        );
    }

    @Scheduled(cron = "@daily")
    @Transactional
    public void loadCurrencyRates() {
        log.debug(LOADING_NEW_CURRENCY_RATES);
        loadCurrencyRate(TENGE_SHORT_NAME);
        loadCurrencyRate(RUBLE_SHORT_NAME);
        loadCurrencyRate(EURO_SHORT_NAME);
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
    protected void loadCurrencyRate(String target) {
        String symbol = getExchangingPair(DOLLAR_SHORT_NAME, target);
        UriComponents uriComponents = buildCurrencyRatesUri(symbol);

        CurrencyRateDto currencyRateDto = restClient.get()
                .uri(uriComponents.toUri())
                .retrieve()
                .toEntity(CurrencyRateDto.class)
                .getBody();

        CurrencyRate currencyRate = CurrencyRate.builder()
                .source(DOLLAR_SHORT_NAME)
                .target(target)
                .rate(currencyRateDto.getClose())
                .datetime(ZonedDateTime.now(ZoneId.of(UTC_ZONE_NAME)).toLocalDateTime())
                .build();

        currencyRatesRepository.save(currencyRate);
        log.info(String.format(NEW_CURRENCY_RATE_WAS_LOADED, target, currencyRate.getRate().doubleValue()));
    }
}
