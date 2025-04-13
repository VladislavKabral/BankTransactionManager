package by.kabral.banktransactionmanager.controller;

import by.kabral.banktransactionmanager.dto.LimitedTransactionListDto;
import by.kabral.banktransactionmanager.dto.TransactionDto;
import by.kabral.banktransactionmanager.dto.TransactionsListDto;
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

  private final TransactionsServiceImpl transactionsService;

  @GetMapping
  public ResponseEntity<TransactionsListDto> getTransactions() throws EntityNotFoundException {
    return new ResponseEntity<>(transactionsService.findAllTransactions(), HttpStatus.OK);
  }

  @GetMapping("/limited")
  public ResponseEntity<LimitedTransactionListDto> getLimitedTransactions() throws EntityNotFoundException {
    return new ResponseEntity<>(transactionsService.findLimitedTransactions(), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<TransactionDto> saveTransaction(@RequestBody @Valid TransactionDto transactionDto) throws InvalidRequestDataException {
    return new ResponseEntity<>(transactionsService.save(transactionDto), HttpStatus.CREATED);
  }
}
