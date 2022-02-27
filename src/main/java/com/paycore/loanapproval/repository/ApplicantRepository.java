package com.paycore.loanapproval.repository;

import com.paycore.loanapproval.entity.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicantRepository extends JpaRepository<Applicant, Integer> {

    Applicant findByIdNumber(String idNumber);

    Applicant findByPhoneNumber(String phoneNumber);

}
