package by.kabral.banktransactionmanager.service;

import by.kabral.banktransactionmanager.dto.LimitDto;
import by.kabral.banktransactionmanager.dto.LimitsListDto;
import by.kabral.banktransactionmanager.exception.InvalidRequestDataException;
import by.kabral.banktransactionmanager.mapper.LimitsMapper;
import by.kabral.banktransactionmanager.model.Limit;
import by.kabral.banktransactionmanager.repository.LimitsRepository;
import by.kabral.banktransactionmanager.util.ExpenseCategory;
import by.kabral.banktransactionmanager.util.validator.LimitsValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static by.kabral.banktransactionmanager.util.TestUtil.*;
import static by.kabral.banktransactionmanager.util.Message.*;

@ExtendWith(MockitoExtension.class)
public class LimitsServiceTest {

  @InjectMocks
  private LimitsServiceImpl limitsService;

  @Mock
  private LimitsRepository limitsRepository;

  @Mock
  private LimitsValidator limitsValidator;

  @Mock
  private LimitsMapper limitsMapper;

  @Test
  public void testFindAllLimitsWhenLimitsExistReturnListOfLimits() {
    //given
    List<Limit> limits = getLimits();
    List<LimitDto> limitDtos = getLimitsDto();

    //when
    when(limitsRepository.findAll()).thenReturn(limits);
    when(limitsMapper.toListDto(limits)).thenReturn(limitDtos);

    //then
    LimitsListDto result = limitsService.findAllLimits();
    assertThat(result.getLimits().size()).isEqualTo(limitDtos.size());
    assertThat(result.getLimits().get(0).getType()).isEqualTo(ExpenseCategory.PRODUCT.name());
    assertThat(result.getLimits().get(0).getValue()).isEqualTo(DEFAULT_LIMIT_VALUE);
    assertThat(result.getLimits().get(1).getType()).isEqualTo(ExpenseCategory.SERVICE.name());
    assertThat(result.getLimits().get(1).getValue()).isEqualTo(DEFAULT_LIMIT_VALUE);
  }

  @Test
  public void testFindAllLimitsWhenLimitsDoNotExistReturnEmptyListOfLimits() {
    //given
    List<Limit> limits = new ArrayList<>();
    List<LimitDto> limitDtos = new ArrayList<>();

    //when
    when(limitsRepository.findAll()).thenReturn(limits);
    when(limitsMapper.toListDto(limits)).thenReturn(limitDtos);

    //then
    LimitsListDto result = limitsService.findAllLimits();
    assertThat(result.getLimits().size()).isEqualTo(0);
  }

  @Test
  public void testFindCurrentLimitByTypeWhenLimitExistReturnLimit() {
    //given
    Limit limit = getLimit();

    //when
    when(limitsRepository.findFirstByTypeOrderByDatetimeDesc(ExpenseCategory.PRODUCT))
            .thenReturn(limit);

    //then
    Limit result = limitsService.findCurrentLimit(ExpenseCategory.PRODUCT);
    assertThat(result.getType()).isEqualTo(ExpenseCategory.PRODUCT);
    assertThat(result.getValue()).isEqualTo(DEFAULT_LIMIT_VALUE);
  }

  @Test
  public void testSaveLimitWhenTheRequestIsValidReturnNewLimit() throws InvalidRequestDataException {
    //given
    LimitDto request = getRequestForNewLimit();
    Limit limit = getLimit();

    //when
    when(limitsMapper.toEntity(request)).thenReturn(limit);
    when(limitsRepository.save(limitsMapper.toEntity(request))).thenReturn(limit);
    when(limitsMapper.toDto(limit)).thenReturn(request);

    //then
    LimitDto result = limitsService.save(request);
    assertThat(result.getType()).isEqualTo(ExpenseCategory.PRODUCT.name());
    assertThat(result.getValue()).isEqualTo(DEFAULT_LIMIT_VALUE);
  }

  @Test
  public void testSaveLimitWhenTheRequestIsInvalidReturnException() throws InvalidRequestDataException {
    //given
    LimitDto request = getInvalidRequestForNewLimit();

    //when
    doThrow(new InvalidRequestDataException(LIMIT_TYPE_IS_UNKNOWN)).when(limitsValidator).validate(request);

    //then
    InvalidRequestDataException exception = assertThrows(InvalidRequestDataException.class,
            () -> limitsService.save(request));
    assertEquals(LIMIT_TYPE_IS_UNKNOWN, exception.getMessage());
  }
}
