package com.paycore.loanapproval.service.impl;

import com.paycore.loanapproval.entity.Applicant;
import com.paycore.loanapproval.exception.NotFoundException;
import com.paycore.loanapproval.repository.ApplicantRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ApplicantServiceImplTest {

    @Mock
    private ApplicantRepository applicantRepository;

    @InjectMocks
    private ApplicantServiceImpl applicantService;

    @Test
    void getApplicant_successful() {

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
    void getApplicant_not_found() {

        //validation
        Assert.assertThrows(NotFoundException.class,
                () -> {
                    applicantService.getApplicant(1);
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
        when(applicantRepository.save(expectedApplicant)).thenReturn(expectedApplicant); // bu satır gerekli midir?

        //then
        applicantService.addApplicant(expectedApplicant);

        //validation
        verify(applicantRepository, times(1)).save(expectedApplicant);

    }

    @Test
    void updateApplicant() {
        //init
        Applicant actualApplicant = new Applicant(1, "98765432101", "Ali", "Tatlı", 4000, "5351234561");
        Applicant updatedApplicant = new Applicant(2, "98765432102", "Veli", "Ekşi", 6000, "5351234562");

        //stub
        when(applicantRepository.save(actualApplicant)).thenReturn(actualApplicant); // bu satır gerekli midir?

        //then
        applicantService.updateApplicant(1, updatedApplicant);

        //validation
        Assert.assertEquals(actualApplicant.getIdNumber(), updatedApplicant.getIdNumber());

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

        //validation
        Assert.assertFalse(NotFoundException.class.equals(applicantService.deleteApplicant(1)));

    }
}