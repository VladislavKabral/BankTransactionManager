package by.kabral.banktransactionmanager.controller;

import by.kabral.banktransactionmanager.dto.LimitDto;
import by.kabral.banktransactionmanager.dto.LimitsListDto;
import by.kabral.banktransactionmanager.service.LimitsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/limits")
@RequiredArgsConstructor
public class LimitsController {

  private final LimitsService limitsService;

  @GetMapping
  public ResponseEntity<LimitsListDto> getLimits() {
    return new ResponseEntity<>(limitsService.findAllLimits(), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<LimitDto> addLimit(@RequestBody @Valid LimitDto limitDto) {
    return new ResponseEntity<>(limitsService.save(limitDto), HttpStatus.CREATED);
  }
}
