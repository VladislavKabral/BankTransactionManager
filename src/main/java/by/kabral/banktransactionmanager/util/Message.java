package by.kabral.banktransactionmanager.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Message {

  public final String ACCOUNT_FROM_IS_BLANK = "The field 'accountFrom' is blank.";
  public final String ACCOUNT_TO_IS_BLANK = "The field 'accountTo' is blank.";
  public final String CURRENCY_SHORTNAME_IS_BLANK = "The field 'currencyShortname' is blank.";
  public final String SUM_IS_NULL = "The field 'sum' is null.";
  public final String EXPENSE_CATEGORY_IS_BLANK = "The field 'expenseCategory' is blank.";
  public final String DATETIME_IS_NULL = "The field 'datetime' is null.";
  public final String ACCOUNT_NAME_LENGTH_IS_INVALID = "The account name length must be 10 characters long.";
  public final String CURRENCY_SHORTNAME_LENGTH_IS_INVALID = "The length of the shortname must be 3 characters long.";
  public final String EXPENSE_CATEGORY_LENGTH_IS_INVALID = "The length of the expense category must be 7 characters long.";
  public final String ACCOUNT_NAME_IS_INVALID = "The account name must contain only 10 digits.";
  public final String CURRENCY_SHORTNAME_IS_INVALID = "The shortname of the currency must contain only 3 characters.";
  public final String EXPENSE_CATEGORY_IS_INVALID = "The expense category must contain only letters.";
  public final String SUM_IS_INVALID = "The sum must be grater than zero.";
  public final String CURRENCY_SHORTNAME_IS_UNKNOWN = "The currency shortname is unknown.";
  public final String EXPENSE_CATEGORY_IS_UNKNOWN = "The expense category is unknown.";
  public final String TRANSACTION_DATETIME_IS_INVALID = "The transaction's datetime is invalid.";
  public final String LIMIT_TYPE_IS_BLANK = "The limit's type is blank.";
  public final String LIMIT_TYPE_LENGTH_IS_INVALID = "The type's length must be 7 characters long.";
  public final String LIMIT_TYPE_IS_INVALID = "The limit's type must contain only letters.";
  public final String LIMIT_VALUE_IS_NULL = "The limit's value is null.";
  public final String LIMIT_VALUE_IS_INVALID = "The limit's value must be grater than zero.";
  public final String LIMIT_TYPE_IS_UNKNOWN = "The limit's type is unknown.";
  public final String METHOD_NOT_ALLOWED = "%s for this endpoint.";
  public final String REQUEST_PARAMETER_IS_INVALID = "Failed to convert value in request parameter.";
}
