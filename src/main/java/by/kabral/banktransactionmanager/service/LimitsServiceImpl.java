package by.kabral.banktransactionmanager.service;

import by.kabral.banktransactionmanager.dto.LimitDto;
import by.kabral.banktransactionmanager.dto.LimitsListDto;
import by.kabral.banktransactionmanager.exception.InvalidRequestDataException;
import by.kabral.banktransactionmanager.mapper.LimitsMapper;
import by.kabral.banktransactionmanager.model.Limit;
import by.kabral.banktransactionmanager.repository.LimitsRepository;
import by.kabral.banktransactionmanager.util.ExpenseCategory;
import by.kabral.banktransactionmanager.util.validator.LimitsValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static by.kabral.banktransactionmanager.util.Constant.*;
import static by.kabral.banktransactionmanager.util.LogMessage.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class LimitsServiceImpl implements EntityService<LimitDto> {

  private final LimitsRepository limitsRepository;
  private final LimitsMapper limitsMapper;
  private final LimitsValidator limitsValidator;

  @Transactional(readOnly = true)
  public LimitsListDto findAllLimits() {
    return LimitsListDto.builder()
            .limits(limitsMapper.toListDto(limitsRepository.findAll()))
            .build();
  }

  @Transactional(readOnly = true)
  public Limit findCurrentLimit(ExpenseCategory type) {
    return limitsRepository.findFirstByTypeOrderByDatetimeDesc(type);
  }

  @Transactional
  public LimitDto save(LimitDto limitDto) throws InvalidRequestDataException {
    log.debug(SAVING_NEW_LIMIT);
    limitsValidator.validate(limitDto);
    Limit limit = limitsMapper.toEntity(limitDto);
    limit.setDatetime(ZonedDateTime.now(ZoneId.of(UTC_ZONE_NAME)));

    LimitDto newLimit = limitsMapper.toDto(limitsRepository.save(limit));
    log.info(String.format(NEW_LIMIT_WAS_SAVED,
            newLimit.getType(),
            newLimit.getValue(),
            newLimit.getDateTime()));

    return newLimit;
  }
}
