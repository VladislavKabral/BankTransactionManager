package by.kabral.banktransactionmanager.util;

import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class Currency {

  public final String DOLLAR_SHORT_NAME = "USD";
  public final String EURO_SHORT_NAME = "EUR";
  public final String TENGE_SHORT_NAME = "KZT";
  public final String RUBLE_SHORT_NAME = "RUB";

  public final int SCALE = 4;

  public final List<String> currencyShortnames = List.of(
          DOLLAR_SHORT_NAME,
          EURO_SHORT_NAME,
          TENGE_SHORT_NAME,
          RUBLE_SHORT_NAME
  );

  public String getExchangingPair(String source, String target) {
    return source + "/" + target;
  }
}
