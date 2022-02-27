package com.paycore.loanapproval.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paycore.loanapproval.entity.Loan;
import com.paycore.loanapproval.entity.RequestStatus;
import com.paycore.loanapproval.exception.handler.GenericExceptionHandler;
import com.paycore.loanapproval.service.impl.LoanRequestServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(MockitoExtension.class)
class LoanRequestControllerTest {

    private MockMvc mvc;

    @Mock
    private LoanRequestServiceImpl loanRequestService;

    @InjectMocks
    private LoanRequestController loanRequestController;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders.standaloneSetup(loanRequestController)
                .setControllerAdvice(new GenericExceptionHandler())
                .build();
    }

    private List<Loan> getTestLoans() {
        List<Loan> loans = new ArrayList<>();
        Loan loan1 = new Loan("98765432101", RequestStatus.APPROVED, 20000, 1250);
        Loan loan2 = new Loan("98765432102", RequestStatus.DENIED, 0, 350);
        Loan loan3 = new Loan("98765432103", RequestStatus.APPROVED, 15000, 850);
        loans.add(loan1);
        loans.add(loan2);
        loans.add(loan3);

        return loans;
    }

    @Test
    void sendRequest() {
    }

    @Test
    void getAllLoanRequest() throws Exception {
        // init
        List<Loan> expectedLoans = getTestLoans();

        // stub
        when(loanRequestController.getAllLoanRequest()).thenReturn(expectedLoans);

        MockHttpServletResponse response = mvc.perform(get("/loan/all")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        List<Loan> actualLoans = new ObjectMapper().readValue(response.getContentAsString(), new TypeReference<List<Loan>>() {});
        assertEquals(expectedLoans.size(), actualLoans.size());
    }

    @Test
    void getLoan() throws Exception {
        // init
        List<Loan> expectedLoans = getTestLoans();

        // stub
        when(loanRequestService.getLoan("98765432101")).thenReturn(expectedLoans.get(0));

        MockHttpServletResponse response = mvc.perform(get("/loan/idNumber/98765432101")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        Loan actualLoan = new ObjectMapper().readValue(response.getContentAsString(), Loan.class);
        Assert.assertEquals(expectedLoans.get(0), actualLoan);
    }
}