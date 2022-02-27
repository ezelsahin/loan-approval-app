package com.paycore.loanapproval.service.impl;

import com.paycore.loanapproval.entity.Applicant;
import com.paycore.loanapproval.exception.NotFoundException;
import com.paycore.loanapproval.repository.ApplicantRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ApplicantServiceImplTest {

    @Mock
    private ApplicantRepository applicantRepository;

    @InjectMocks
    private ApplicantServiceImpl applicantService;


    @Test
    void getApplicant_by_id_successful() {

        //init
        Applicant expectedApplicant = new Applicant(1, "98765432101", "Ali", "Tatlı", 4000, "5351234561");

        //stub
        when(applicantRepository.findById(1)).thenReturn(Optional.of(expectedApplicant));

        //then
        Applicant actualApplicant = applicantService.getApplicant(1);

        //validation
        Assert.assertEquals(expectedApplicant.getIdNumber(), actualApplicant.getIdNumber());

    }

    @Test
    void getApplicant_by_id_number_successful() {

        //init
        Applicant expectedApplicant = new Applicant(1, "98765432101", "Ali", "Tatlı", 4000, "5351234561");

        //stub
        when(applicantRepository.findByIdNumber("98765432101")).thenReturn(expectedApplicant);

        //then
        Applicant actualApplicant = applicantService.getApplicant("98765432101");

        //validation
        Assert.assertEquals(expectedApplicant.getIdNumber(), actualApplicant.getIdNumber());

    }

    @Test
    void getApplicant_by_id_not_found() {

        //validation
        assertThrows(NotFoundException.class,
                () -> {
                    applicantService.getApplicant(1);
                }

        );
    }

    @Test
    void getApplicant_by_id_number_not_found() {

        //validation
        assertThrows(NotFoundException.class,
                () -> {
                    applicantService.getApplicant("98765432101");
                }

        );
    }

    @Test
    void getAllApplicants() {
        //init
        Applicant applicant1 = new Applicant(1, "98765432101", "Ali", "Tatlı", 4000, "5351234561");
        Applicant applicant2 = new Applicant(2, "98765432102", "Veli", "Ekşi", 6000, "5351234562");
        List<Applicant> expectedApplicants = new ArrayList<>();
        expectedApplicants.add(applicant1);
        expectedApplicants.add(applicant2);

        //stub
        when(applicantRepository.findAll()).thenReturn(expectedApplicants);

        //then
        List<Applicant> actualApplicants = applicantService.getAllApplicants();
        Assert.assertEquals(expectedApplicants.size(), actualApplicants.size());

    }

    @Test
    void addApplicant() {
        //init
        Applicant expectedApplicant = new Applicant(1, "98765432101", "Ali", "Tatlı", 4000, "5351234561");

        //stub
        when(applicantRepository.save(expectedApplicant)).thenReturn(expectedApplicant);

        //then
        applicantService.addApplicant(expectedApplicant);

        //validation
        verify(applicantRepository, times(1)).save(expectedApplicant);

    }

    @Test
    void updateApplicant() {
        //init
        List<Applicant> applicants = new ArrayList<>();
        Applicant actualApplicant = new Applicant(1, "98765432101", "Ali", "Tatlı", 4000, "5351234561");
        applicants.add(actualApplicant);
        Applicant expectedApplicant = applicants.get(0);
        expectedApplicant.setFirstName("Veli");
        expectedApplicant.setMonthlyIncome(6000);

        //then
        applicantService.updateApplicant(1, expectedApplicant);

        //validation
        Assert.assertEquals(applicants.get(0).getFirstName(), expectedApplicant.getFirstName());
    }

    @Test
    void deleteApplicant() {
        //init
        Applicant applicant = new Applicant(1, "98765432101", "Ali", "Tatlı", 4000, "5351234561");

        //then
        applicantService.deleteApplicant(1);

        //validation
        verify(applicantRepository, times(1)).deleteById(1);

    }

    @Test
    void deleteApplicant_not_found() {
        int mockID = 111;
        doThrow(EmptyResultDataAccessException.class).when(applicantRepository).deleteById(mockID);

        //validation
        NotFoundException nfe = assertThrows(
                NotFoundException.class,
                () ->applicantService.deleteApplicant(mockID));
        assertEquals(nfe.getMessage(), String.format("%d not found!", mockID));

    }
}