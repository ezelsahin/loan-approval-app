package com.paycore.loanapproval.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table
public class Applicant {

    @Id
    private Long idNumber;

    private String firstName;

    private String lastName;

    private Long monthlySalary;

    private Long phoneNumber;


}
