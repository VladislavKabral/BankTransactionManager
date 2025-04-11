package by.kabral.banktransactionmanager.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Regex {

  public final String ACCOUNT_NAME_REGEX = "\\d{10}";
  public final String CURRENCY_SHORTNAME_REGEX = "[A-Za-z]{10}";
  public final String EXPENSE_CATEGORY_REGEX = "^[a-zA-Z]+$";
  public final String LIMIT_TYPE_REGEX = "^[a-zA-Z]+$";
}
