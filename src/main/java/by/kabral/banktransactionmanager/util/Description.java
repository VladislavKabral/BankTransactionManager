package by.kabral.banktransactionmanager.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Description {

  public final String TRANSACTION_DESCRIPTION = "Data transfer object for a transaction";
  public final String TRANSACTION_ID_DESCRIPTION = "The ID of the transaction";
  public final String TRANSACTION_ID_EXAMPLE = "4a63509a-0761-4f91-b1b9-715b9f14af4c";
  public final String TRANSACTION_ACCOUNT_FROM_DESCRIPTION = "The ID of the account, which sent the transaction";
  public final String TRANSACTION_ACCOUNT_FROM_EXAMPLE = "1234567890";
  public final String TRANSACTION_ACCOUNT_TO_DESCRIPTION = "The ID of the account, which received the transaction";
  public final String TRANSACTION_ACCOUNT_TO_EXAMPLE = "1234567890";
  public final String TRANSACTION_CURRENCY_SHORTNAME_DESCRIPTION = "The shortname of the transaction's currency";
  public final String TRANSACTION_CURRENCY_SHORTNAME_EXAMPLE = "USD";
  public final String TRANSACTION_SUM_DESCRIPTION = "The value of the transaction";
  public final String TRANSACTION_SUM_EXAMPLE = "1000.00";
  public final String TRANSACTION_EXPENSE_CATEGORY_DESCRIPTION = "The category of the transaction. Two variants are possible: PRODUCT and SERVICE";
  public final String TRANSACTION_EXPENSE_CATEGORY_EXAMPLE = "SERVICE";
  public final String TRANSACTION_DATETIME_DESCRIPTION = "The date and the time of the transaction";
  public final String TRANSACTION_DATETIME_EXAMPLE = "2025-04-11 01:00:00.196000 +00:00";
  public final String TRANSACTION_IS_LIMIT_EXCEEDED_DESCRIPTION = "The flag, which means the transaction exceeded the limit or not";
  public final String TRANSACTION_IS_LIMIT_EXCEEDED_EXAMPLE = "true";

  public final String TRANSACTIONS_LIST_DESCRIPTION = "Data transfer object for a list of the transactions";
  public final String TRANSACTIONS_LIST_VALUE_DESCRIPTION = "The list of the transactions";

  public final String LIMIT_DESCRIPTION = "Data transfer object for a limit";
  public final String LIMIT_TYPE_DESCRIPTION = "The type of the limit. Two variants are possible: PRODUCT and SERVICE";
  public final String LIMIT_TYPE_EXAMPLE = "SERVICE";
  public final String LIMIT_VALUE_DESCRIPTION = "The value of the limit";
  public final String LIMIT_VALUE_EXAMPLE = "1000.00";
  public final String LIMIT_DATETIME_DESCRIPTION = "The date and the time, where the limit was saved";
  public final String LIMIT_DATETIME_EXAMPLE = "2025-04-11 01:00:00.196000 +00:00";

  public final String LIMITED_TRANSACTION_DESCRIPTION = "Data transfer object for the transaction, which exceeded the limit";
  public final String LIMITED_TRANSACTION_LIMIT_DATETIME_DESCRIPTION = "The date and the time of the transaction's limit";
  public final String LIMITED_TRANSACTION_LIMIT_DATETIME_EXAMPLE = "2025-04-11 01:00:00.196000 +00:00";
  public final String LIMITED_TRANSACTION_LIMIT_SUM_DESCRIPTION = "The value of the transaction's limit";
  public final String LIMITED_TRANSACTION_LIMIT_SUM_EXAMPLE = "1000.00";
  public final String LIMITED_TRANSACTION_LIMIT_CURRENCY_SHORTNAME_DESCRIPTION = "The shortname of the limit's currency";
  public final String LIMITED_TRANSACTION_LIMIT_CURRENCY_SHORTNAME_EXAMPLE = "USD";

  public final String LIMITS_DESCRIPTION = "Data transfer object for the limits";
  public final String LIMITS_VALUE_DESCRIPTION = "The list of the limits";

  public final String LIMITED_TRANSACTIONS_DESCRIPTION = "Data transfer object for the limited transactions";
  public final String LIMITED_TRANSACTIONS_VALUE_DESCRIPTION = "The list of the transactions, which exceeded the limit or not";
}
