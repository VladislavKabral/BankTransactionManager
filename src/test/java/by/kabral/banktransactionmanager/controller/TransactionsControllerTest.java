package by.kabral.banktransactionmanager.controller;

import by.kabral.banktransactionmanager.dto.LimitedTransactionListDto;
import by.kabral.banktransactionmanager.dto.TransactionDto;
import by.kabral.banktransactionmanager.dto.TransactionsListDto;
import by.kabral.banktransactionmanager.service.TransactionsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static by.kabral.banktransactionmanager.util.Message.ACCOUNT_NAME_IS_INVALID;
import static by.kabral.banktransactionmanager.util.TestUtil.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(TransactionsController.class)
public class TransactionsControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private TransactionsServiceImpl transactionsService;

  private final ObjectMapper objectMapper = new ObjectMapper()
          .registerModule(new JavaTimeModule())
          .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

  @Test
  public void testGetTransactionsWhenTransactionsExistReturnListOfTransactions() throws Exception {
    //given
    TransactionsListDto transactionsList = new TransactionsListDto(getTransactionsDto());

    //when
    when(transactionsService.findAllTransactions()).thenReturn(transactionsList);

    //then
    mockMvc.perform(get("/transactions")
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.transactions.length()").value(1))
            .andExpect(jsonPath("$.transactions[0].accountFrom")
                    .value(transactionsList.getTransactions().get(0).getAccountFrom()))
            .andExpect(jsonPath("$.transactions[0].accountTo")
                    .value(transactionsList.getTransactions().get(0).getAccountTo()))
            .andExpect(jsonPath("$.transactions[0].sum")
                    .value(transactionsList.getTransactions().get(0).getSum()))
            .andExpect(jsonPath("$.transactions[0].currencyShortName")
                    .value(transactionsList.getTransactions().get(0).getCurrencyShortName()));
  }

  @Test
  public void testGetTransactionsWhenTransactionsDoNotExistReturnEmptyListOfTransactions() throws Exception {
    //given
    TransactionsListDto transactionsList = new TransactionsListDto(getEmptyTransactionsDto());

    //when
    when(transactionsService.findAllTransactions()).thenReturn(transactionsList);

    //then
    mockMvc.perform(get("/transactions")
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.transactions.length()").value(0));
  }

  @Test
  public void testGetLimitedTransactionsWhenTransactionsExistReturnListOfTransactions() throws Exception {
    //given
    LimitedTransactionListDto transactionsList = new LimitedTransactionListDto(getLimitedTransactionsDto());

    //when
    when(transactionsService.findLimitedTransactions()).thenReturn(transactionsList);

    //then
    mockMvc.perform(get("/transactions/limited")
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.transactions.length()").value(1))
            .andExpect(jsonPath("$.transactions[0].accountFrom")
                    .value(transactionsList.getTransactions().get(0).getAccountFrom()))
            .andExpect(jsonPath("$.transactions[0].accountTo")
                    .value(transactionsList.getTransactions().get(0).getAccountTo()))
            .andExpect(jsonPath("$.transactions[0].sum")
                    .value(transactionsList.getTransactions().get(0).getSum()))
            .andExpect(jsonPath("$.transactions[0].currencyShortName")
                    .value(transactionsList.getTransactions().get(0).getCurrencyShortName()))
            .andExpect(jsonPath("$.transactions[0].limitSum")
                    .value(transactionsList.getTransactions().get(0).getLimitSum()))
            .andExpect(jsonPath("$.transactions[0].limitCurrencyShortname")
                    .value(transactionsList.getTransactions().get(0).getLimitCurrencyShortname()));
  }


  @Test
  public void testSaveNewTransactionWhenTheRequestIsValidReturnNewTransaction() throws Exception {
    //given
    TransactionDto request = getRequestForNewTransaction();
    TransactionDto transaction = getTransactionDto();

    //when
    when(transactionsService.save(request)).thenReturn(transaction);

    //then
    mockMvc.perform(post("/transactions")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").isNotEmpty())
            .andExpect(jsonPath("$.accountFrom")
                    .value(transaction.getAccountFrom()))
            .andExpect(jsonPath("$.accountTo")
                    .value(transaction.getAccountTo()))
            .andExpect(jsonPath("$.sum")
                    .value(transaction.getSum()))
            .andExpect(jsonPath("$.currencyShortName")
                    .value(transaction.getCurrencyShortName()))
            .andExpect(jsonPath("$.limitExceeded")
                    .value(transaction.isLimitExceeded()));
  }

  @Test
  public void testSaveNewTransactionWhenTheRequestIsInvalidReturnTheMessageWithError() throws Exception {
    //given

    //when

    //then
    mockMvc.perform(post("/transactions")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(getInValidRequestForNewTransaction())))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.message").value(ACCOUNT_NAME_IS_INVALID));
  }
}
