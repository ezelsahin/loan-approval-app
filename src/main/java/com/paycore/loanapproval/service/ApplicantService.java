package com.paycore.loanapproval.service;

import com.paycore.loanapproval.entity.Applicant;

import java.util.List;

public interface ApplicantService {

    Applicant getApplicant(int id); // kullanıcıyı getir

    List<Applicant> getAllApplicants(); // tüm kullanıcıları getir

    Applicant addApplicant(Applicant applicant); // yeni kullanıcı ekle

    Applicant updateApplicant(int id, Applicant applicant); // kullanıcıyı güncelle

    boolean deleteApplicant(int id); // kullanıcı sil

}