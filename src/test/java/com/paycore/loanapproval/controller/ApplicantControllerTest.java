package com.paycore.loanapproval.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.paycore.loanapproval.entity.Applicant;
import com.paycore.loanapproval.exception.handler.GenericExceptionHandler;
import com.paycore.loanapproval.repository.ApplicantRepository;
import com.paycore.loanapproval.service.impl.ApplicantServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(MockitoExtension.class)
class ApplicantControllerTest {

    private MockMvc mvc;

    @Mock
    private ApplicantServiceImpl applicantService;

    @Mock
    private ApplicantRepository applicantRepository;

    @InjectMocks
    private ApplicantController applicantController;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders.standaloneSetup(applicantController)
                .setControllerAdvice(new GenericExceptionHandler())
                .build();
    }

    private List<Applicant> getTestApplicants() {
        List<Applicant> applicants = new ArrayList<>();
        Applicant applicant1 = new Applicant(1, "98765432101", "Ali", "Sakin", 4000, "5351234561");
        Applicant applicant2 = new Applicant(2, "98765432102", "Veli", "Fevri", 6000, "5351234562");
        applicants.add(applicant1);
        applicants.add(applicant2);

        return applicants;
    }

    @Test
    void getAllApplicants() throws Exception {
        // init
        List<Applicant> expectedApplicants = getTestApplicants();

        // stub
        when(applicantController.getAllApplicants()).thenReturn(expectedApplicants);

        MockHttpServletResponse response = mvc.perform(get("/applicant/all")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        List<Applicant> actualApplicants = new ObjectMapper().readValue(response.getContentAsString(), new TypeReference<List<Applicant>>() {});
        assertEquals(expectedApplicants.size(), actualApplicants.size());

    }

    @Test
    void getApplicant_by_id() throws Exception {
        // init
        List<Applicant> expectedApplicants = getTestApplicants();

        // stub
        when(applicantService.getApplicant(1)).thenReturn(expectedApplicants.get(0));

        MockHttpServletResponse response = mvc.perform(get("/applicant/id/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        Applicant actualApplicant = new ObjectMapper().readValue(response.getContentAsString(), Applicant.class);
        Assert.assertEquals(expectedApplicants.get(0), actualApplicant);
    }

    @Test
    void getApplicant_by_id_number() throws Exception {
        // init
        List<Applicant> expectedApplicants = getTestApplicants();

        // stub
        when(applicantService.getApplicant("98765432101")).thenReturn(expectedApplicants.get(0));

        MockHttpServletResponse response = mvc.perform(get("/applicant/98765432101")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        Applicant actualApplicant = new ObjectMapper().readValue(response.getContentAsString(), Applicant.class);
        Assert.assertEquals(expectedApplicants.get(0), actualApplicant);

    }

    @Test
    void saveApplicant() throws Exception {
        // init
        List<Applicant> expectedApplicants = getTestApplicants();
        Applicant expectedApplicant = new Applicant(3,"98765432103", "Zeynep", "Uzun", 7000, "5351234563");
        expectedApplicants.add(expectedApplicant);

        // stub
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String expectedApplicantJsonStr = ow.writeValueAsString(expectedApplicant);
        when(applicantService.addApplicant(expectedApplicant)).thenReturn(expectedApplicant);

        MockHttpServletResponse response = mvc.perform(post("/applicant/add")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(expectedApplicantJsonStr))
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        Mockito.verify(applicantService, Mockito.times(1)).addApplicant(any());
    }

    @Test
    void updateApplicant() throws Exception {
        // init
        List<Applicant> expectedApplicants = getTestApplicants();
        Applicant expectedApplicant = expectedApplicants.get(0);
        expectedApplicant.setFirstName("Putin");
        expectedApplicants.add(expectedApplicant);

        // stub
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String expectedApplicantJsonStr = ow.writeValueAsString(expectedApplicant);

        MockHttpServletResponse response = mvc.perform(put("/applicant/update/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(expectedApplicantJsonStr))
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        Assert.assertEquals(expectedApplicants.get(0), expectedApplicant);
    }

    @Test
    void deleteApplicant() throws Exception {
        // stub
        MockHttpServletResponse response = mvc.perform(delete("/applicant/delete/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        String actualResponseStr = response.getContentAsString();
        Assert.assertEquals("true", actualResponseStr);
    }
}