package by.kabral.banktransactionmanager.controller;

import by.kabral.banktransactionmanager.dto.LimitedTransactionListDto;
import by.kabral.banktransactionmanager.dto.TransactionDto;
import by.kabral.banktransactionmanager.dto.TransactionsListDto;
import by.kabral.banktransactionmanager.exception.EntityNotFoundException;
import by.kabral.banktransactionmanager.exception.InvalidRequestDataException;
import by.kabral.banktransactionmanager.service.TransactionsServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "TransactionsController", description = "The controller for the transactions")
@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionsController {

  private final TransactionsServiceImpl transactionsService;

  @Operation(
          summary = "Getting all the transactions",
          description = "Allows to get all the transactions with the flag 'limit_exceeded'"
  )
  @GetMapping
  public ResponseEntity<TransactionsListDto> getTransactions() {
    return new ResponseEntity<>(transactionsService.findAllTransactions(), HttpStatus.OK);
  }

  @Operation(
          summary = "Getting only limited transactions",
          description = "Allows to get the transactions, which exceeded the limit"
  )
  @GetMapping("/limited")
  public ResponseEntity<LimitedTransactionListDto> getLimitedTransactions() {
    return new ResponseEntity<>(transactionsService.findLimitedTransactions(), HttpStatus.OK);
  }

  @Operation(
          summary = "Saving a new transaction",
          description = "Allows to save a new transaction"
  )
  @PostMapping
  public ResponseEntity<TransactionDto> saveTransaction(@RequestBody @Valid
                                                          @Parameter(description = "Dto with the data of the transaction", required = true)
                                                          TransactionDto transactionDto) throws InvalidRequestDataException, EntityNotFoundException {
    return new ResponseEntity<>(transactionsService.save(transactionDto), HttpStatus.CREATED);
  }
}
