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

import static by.kabral.banktransactionmanager.util.Description.*;

@Tag(name = TRANSACTIONS_CONTROLLER_NAME, description = TRANSACTIONS_CONTROLLER_DESCRIPTION)
@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionsController {

  private final TransactionsServiceImpl transactionsService;

  @Operation(
          summary = TRANSACTIONS_CONTROLLER_GET_TRANSACTIONS_SUMMARY,
          description = TRANSACTIONS_CONTROLLER_GET_TRANSACTIONS_DESCRIPTION
  )
  @GetMapping
  public ResponseEntity<TransactionsListDto> getTransactions() {
    return new ResponseEntity<>(transactionsService.findAllTransactions(), HttpStatus.OK);
  }

  @Operation(
          summary = TRANSACTIONS_CONTROLLER_GET_LIMITED_TRANSACTIONS_SUMMARY,
          description = TRANSACTIONS_CONTROLLER_GET_LIMITED_TRANSACTIONS_DESCRIPTION
  )
  @GetMapping("/limited")
  public ResponseEntity<LimitedTransactionListDto> getLimitedTransactions() {
    return new ResponseEntity<>(transactionsService.findLimitedTransactions(), HttpStatus.OK);
  }

  @Operation(
          summary = TRANSACTIONS_CONTROLLER_SAVE_TRANSACTION_SUMMARY,
          description = TRANSACTIONS_CONTROLLER_SAVE_TRANSACTION_DESCRIPTION
  )
  @PostMapping
  public ResponseEntity<TransactionDto> saveTransaction(@RequestBody @Valid
                                                          @Parameter(description = TRANSACTIONS_CONTROLLER_SAVE_TRANSACTION_PARAM_DESCRIPTION, required = true)
                                                          TransactionDto transactionDto) throws InvalidRequestDataException, EntityNotFoundException {
    return new ResponseEntity<>(transactionsService.save(transactionDto), HttpStatus.CREATED);
  }
}
