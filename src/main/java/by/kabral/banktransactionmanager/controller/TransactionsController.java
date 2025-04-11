package by.kabral.banktransactionmanager.controller;

import by.kabral.banktransactionmanager.dto.TransactionDto;
import by.kabral.banktransactionmanager.service.TransactionsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionsController {

  private final TransactionsService transactionsService;

  @PostMapping
  public ResponseEntity<TransactionDto> saveTransaction(@RequestBody @Valid TransactionDto transactionDto) {
    return new ResponseEntity<>(transactionsService.save(transactionDto), HttpStatus.CREATED);
  }
}
