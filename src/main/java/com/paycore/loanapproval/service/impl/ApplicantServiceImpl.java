package com.paycore.loanapproval.service.impl;

import com.paycore.loanapproval.entity.Applicant;
import com.paycore.loanapproval.repository.ApplicantRepository;
import com.paycore.loanapproval.service.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicantServiceImpl implements ApplicantService {

    @Autowired
    private ApplicantRepository applicantRepository;

    public Applicant getApplicant(int id){
        return applicantRepository.findById(id).get();
    };

    @Override
    public List<Applicant> getAllApplicants(){
        return applicantRepository.findAll();
    };

    @Override
    public Applicant addApplicant(Applicant applicant){
        applicantRepository.save(applicant);
        return applicant;
    }

    @Override
    public Applicant updateApplicant(int id, Applicant applicant){
        getApplicant(id);
        applicant.setId(id);
        return applicantRepository.save(applicant);
    };

    @Override
    public boolean deleteApplicant(int id){
        applicantRepository.delete(getApplicant(id));
        return true;
    };

}
