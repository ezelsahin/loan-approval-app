package com.paycore.loanapproval.service.impl;

import com.paycore.loanapproval.entity.Applicant;
import com.paycore.loanapproval.exception.NotFoundException;
import com.paycore.loanapproval.repository.ApplicantRepository;
import com.paycore.loanapproval.service.ApplicantService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApplicantServiceImpl implements ApplicantService {

    @Autowired
    private ApplicantRepository applicantRepository;

    @Override
    public Applicant getApplicant(int id){
        return applicantRepository.findById(id).orElseThrow(() -> new NotFoundException("Applicant"));
    };

    @Override
    public Applicant getApplicant(String idNumber){
        Optional<Applicant> byIdNumber = Optional.ofNullable(applicantRepository.findByIdNumber(idNumber));
        return byIdNumber.orElseThrow(() -> new NotFoundException("Applicant"));
    };

    @Override
    public List<Applicant> getAllApplicants(){
        return applicantRepository.findAll();
    };

    @Override
    public Applicant addApplicant(Applicant applicant){
        return applicantRepository.save(applicant);
    }

    @Override
    public boolean updateApplicant(int id, Applicant applicant){
        getApplicant(id);
        applicant.setId(id);
        applicantRepository.save(applicant);
        return true;
    };

    @Override
    public boolean deleteApplicant(int id){
        applicantRepository.deleteById(id);
        return true;
    };

}
