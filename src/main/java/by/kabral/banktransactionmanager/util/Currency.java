package by.kabral.banktransactionmanager.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Currency {

  public final String DOLLAR_SHORT_NAME = "USD";
  public final String EURO_SHORT_NAME = "EUR";
  public final String TENGE_SHORT_NAME = "KZT";
  public final String RUBLE_SHORT_NAME = "RUB";

  public String getExchangingPair(String source, String target) {
    return source + "/" + target;
  }
}
