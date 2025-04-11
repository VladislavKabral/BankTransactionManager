package by.kabral.banktransactionmanager.service;

import by.kabral.banktransactionmanager.dto.LimitDto;
import by.kabral.banktransactionmanager.dto.LimitsListDto;
import by.kabral.banktransactionmanager.mapper.LimitsMapper;
import by.kabral.banktransactionmanager.model.Limit;
import by.kabral.banktransactionmanager.repository.LimitsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static by.kabral.banktransactionmanager.util.Constant.*;

@Service
@RequiredArgsConstructor
public class LimitsService {

  private final LimitsRepository limitsRepository;
  private final LimitsMapper limitsMapper;

  @Transactional(readOnly = true)
  public LimitsListDto findAllLimits() {
    return LimitsListDto.builder()
            .limits(limitsMapper.toListDto(limitsRepository.findAll()))
            .build();
  }

  @Transactional
  public LimitDto save(LimitDto limitDto) {
    Limit limit = limitsMapper.toEntity(limitDto);
    limit.setDateTime(ZonedDateTime.now(ZoneId.of(UTC_ZONE_NAME)));

    return limitsMapper.toDto(limitsRepository.save(limit));
  }
}
