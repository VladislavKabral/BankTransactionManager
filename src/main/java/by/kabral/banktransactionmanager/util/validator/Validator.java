package by.kabral.banktransactionmanager.util.validator;

import by.kabral.banktransactionmanager.exception.InvalidRequestDataException;

public interface Validator<T> {
  void validate(T t) throws InvalidRequestDataException;
}
