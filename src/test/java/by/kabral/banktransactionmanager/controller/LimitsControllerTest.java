package by.kabral.banktransactionmanager.controller;

import by.kabral.banktransactionmanager.dto.LimitDto;
import by.kabral.banktransactionmanager.dto.LimitsListDto;
import by.kabral.banktransactionmanager.service.LimitsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static by.kabral.banktransactionmanager.util.Message.*;
import static by.kabral.banktransactionmanager.util.TestUtil.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(LimitsController.class)
public class LimitsControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private LimitsServiceImpl limitsService;

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Test
  public void testGetLimitsWhenLimitsExistReturnListOfLimits() throws Exception {
    //given
    LimitsListDto limitsList = new LimitsListDto(getLimitsDto());

    //when
    when(limitsService.findAllLimits()).thenReturn(limitsList);

    //then
    mockMvc.perform(get("/limits")
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.limits.length()").value(2))
            .andExpect(jsonPath("$.limits[0].type").value(limitsList.getLimits().get(0).getType()))
            .andExpect(jsonPath("$.limits[0].value").value(limitsList.getLimits().get(0).getValue()))
            .andExpect(jsonPath("$.limits[1].type").value(limitsList.getLimits().get(1).getType()))
            .andExpect(jsonPath("$.limits[1].value").value(limitsList.getLimits().get(1).getValue()));
  }

  @Test
  public void testGetLimitsWhenLimitsDoNotExistReturnEmptyListOfLimits() throws Exception {
    //given
    LimitsListDto limitsList = new LimitsListDto(getEmptyLimitsDto());

    //when
    when(limitsService.findAllLimits()).thenReturn(limitsList);

    //then
    mockMvc.perform(get("/limits")
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.limits.length()").value(0));
  }

  @Test
  public void testAddLimitWhenRequestIsValidReturnNewLimit() throws Exception {
    //given
    LimitDto limit = getLimit();
    LimitDto request = getRequestForNewLimit();

    //when
    when(limitsService.save(request)).thenReturn(limit);

    //then
    mockMvc.perform(post("/limits")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.type").value(limit.getType()))
            .andExpect(jsonPath("$.value").value(limit.getValue()));
  }

  @Test
  public void testAddLimitWhenRequestIsInvalidReturnMessageWithError() throws Exception {
    //given

    //when

    //then
    mockMvc.perform(post("/limits")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(getInvalidRequestForNewLimit())))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.message").value(LIMIT_TYPE_IS_INVALID));
  }

}
