package by.kabral.banktransactionmanager.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class LogMessage {

  public final String LOADING_NEW_CURRENCY_RATES = "Loading new currency rates...";
  public final String NEW_CURRENCY_RATE_WAS_LOADED = "The currency rate for '%s' was loaded. New rate is '%f'.";
  public final String NEW_LIMIT_WAS_SAVED = "The new limit with type '%s' was was saved. The value is'%f', the datetime is '%s.'";
  public final String NEW_TRANSACTION_WAS_SAVED = "The new transaction with currency '%s' and value '%f' was was saved.";
  public final String IS_LIMIT_EXCEEDED = "Exceeding the limit: %b.";
  public final String SAVING_NEW_TRANSACTION = "Saving new transaction...";
  public final String SAVING_NEW_LIMIT = "Saving new limit...";
}
