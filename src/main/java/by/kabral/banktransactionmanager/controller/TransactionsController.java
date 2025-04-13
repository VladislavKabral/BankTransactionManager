package by.kabral.banktransactionmanager.controller;

import by.kabral.banktransactionmanager.dto.LimitedTransactionListDto;
import by.kabral.banktransactionmanager.dto.TransactionDto;
import by.kabral.banktransactionmanager.exception.EntityNotFoundException;
import by.kabral.banktransactionmanager.exception.InvalidRequestDataException;
import by.kabral.banktransactionmanager.service.TransactionsServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionsController {

  private final TransactionsServiceImpl transactionsServiceImpl;

  @GetMapping("/limited")
  public ResponseEntity<LimitedTransactionListDto> getLimitedTransactions() throws EntityNotFoundException {
    return new ResponseEntity<>(transactionsServiceImpl.findLimitedTransactions(), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<TransactionDto> saveTransaction(@RequestBody @Valid TransactionDto transactionDto) throws InvalidRequestDataException {
    return new ResponseEntity<>(transactionsServiceImpl.save(transactionDto), HttpStatus.CREATED);
  }
}
