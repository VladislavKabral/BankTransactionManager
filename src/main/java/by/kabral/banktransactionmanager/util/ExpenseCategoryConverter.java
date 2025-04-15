package by.kabral.banktransactionmanager.util;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import static by.kabral.banktransactionmanager.util.Message.*;

@Converter(autoApply = true)
public class ExpenseCategoryConverter implements AttributeConverter<ExpenseCategory, Integer> {

  @Override
  public Integer convertToDatabaseColumn(ExpenseCategory attribute) {
    if (attribute == null) {
      return null;
    }
    return switch (attribute) {
      case PRODUCT -> 0;
      case SERVICE -> 1;
    };
  }

  @Override
  public ExpenseCategory convertToEntityAttribute(Integer dbData) {
    if (dbData == null) {
      return null;
    }

    return switch (dbData) {
      case 0 -> ExpenseCategory.PRODUCT;
      case 1 -> ExpenseCategory.SERVICE;
      default -> throw new IllegalArgumentException(String.format(UNKNOWN_EXPENSE_CATEGORY_VALUE, dbData));
    };
  }
}
