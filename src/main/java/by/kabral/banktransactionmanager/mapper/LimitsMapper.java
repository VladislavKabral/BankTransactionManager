package by.kabral.banktransactionmanager.mapper;

import by.kabral.banktransactionmanager.dto.LimitDto;
import by.kabral.banktransactionmanager.model.Limit;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LimitsMapper {

  private final ModelMapper modelMapper;

  public Limit toEntity(LimitDto limitDto) {
    return modelMapper.map(limitDto, Limit.class);
  }

  public LimitDto toDto(Limit limit) {
    return modelMapper.map(limit, LimitDto.class);
  }

  public List<LimitDto> toListDto(List<Limit> limits) {
    return limits.stream()
            .map(this::toDto)
            .toList();
  }
}
