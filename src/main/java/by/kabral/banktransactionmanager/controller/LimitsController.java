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

import static by.kabral.banktransactionmanager.util.Description.*;

@Tag(name = LIMITS_CONTROLLER_NAME, description = LIMITS_CONTROLLER_DESCRIPTION)
@RestController
@RequestMapping("/limits")
@RequiredArgsConstructor
public class LimitsController {

  private final LimitsServiceImpl limitsService;

  @Operation(
          summary = LIMITS_CONTROLLER_GET_LIMITS_SUMMARY,
          description = LIMITS_CONTROLLER_GET_LIMITS_DESCRIPTION
  )
  @GetMapping
  public ResponseEntity<LimitsListDto> getLimits() {
    return new ResponseEntity<>(limitsService.findAllLimits(), HttpStatus.OK);
  }

  @Operation(
          summary = LIMITS_CONTROLLER_ADD_LIMIT_SUMMARY,
          description = LIMITS_CONTROLLER_ADD_LIMIT_DESCRIPTION
  )
  @PostMapping
  public ResponseEntity<LimitDto> addLimit(@RequestBody @Valid
                                             @Parameter(description = LIMITS_CONTROLLER_ADD_LIMIT_PARAM_DESCRIPTION, required = true)
                                             LimitDto limitDto) throws InvalidRequestDataException {
    return new ResponseEntity<>(limitsService.save(limitDto), HttpStatus.CREATED);
  }
}
