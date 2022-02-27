package com.paycore.loanapproval.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "applicant")
public class Applicant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private String idNumber;

    private String firstName;

    private String lastName;

    private int monthlyIncome;

    private String phoneNumber;


}
