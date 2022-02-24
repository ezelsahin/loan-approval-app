package com.paycore.loanapproval.repository;

import com.paycore.loanapproval.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanResultRepository extends JpaRepository<Loan, Integer> {
    Loan findByIdNumber(String idNumber);

}
