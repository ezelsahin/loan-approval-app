package com.paycore.loanapproval.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "applicant")
public class Applicant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "idNumber is mandatory")
    private String idNumber;

    @NotBlank(message = "firstName is mandatory")
    private String firstName;

    @NotBlank(message = "lastName is mandatory")
    private String lastName;

    @NotNull(message = "monthlyIncome is mandatory")
    private int monthlyIncome;

    @NotBlank(message = "phoneNumber is mandatory")
    private String phoneNumber;


}
