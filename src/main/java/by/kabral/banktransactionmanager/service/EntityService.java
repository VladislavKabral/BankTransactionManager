package by.kabral.banktransactionmanager.service;

import by.kabral.banktransactionmanager.exception.InvalidRequestDataException;

public interface EntityService<T> {
  T save(T entity) throws InvalidRequestDataException;
}
