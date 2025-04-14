package by.kabral.banktransactionmanager.controller;

import by.kabral.banktransactionmanager.dto.LimitDto;
import by.kabral.banktransactionmanager.dto.LimitsListDto;
import by.kabral.banktransactionmanager.exception.InvalidRequestDataException;
import by.kabral.banktransactionmanager.service.LimitsServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "LimitsController", description = "The controller for the limits")
@RestController
@RequestMapping("/limits")
@RequiredArgsConstructor
public class LimitsController {

  private final LimitsServiceImpl limitsService;

  @Operation(
          summary = "Getting all the limits",
          description = "Allows to get all the limits form the database"
  )
  @GetMapping
  public ResponseEntity<LimitsListDto> getLimits() {
    return new ResponseEntity<>(limitsService.findAllLimits(), HttpStatus.OK);
  }

  @Operation(
          summary = "Saving a new limit",
          description = "Allows to save a new limit"
  )
  @PostMapping
  public ResponseEntity<LimitDto> addLimit(@RequestBody @Valid
                                             @Parameter(description = "Dto with the data of the limit", required = true)
                                             LimitDto limitDto) throws InvalidRequestDataException {
    return new ResponseEntity<>(limitsService.save(limitDto), HttpStatus.CREATED);
  }
}
