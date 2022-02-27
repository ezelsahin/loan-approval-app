package com.paycore.loanapproval.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "request_result")
public class Loan {

    @Id
    private String idNumber;

    @Enumerated(EnumType.STRING)
    private RequestStatus requestStatus;

    private int loanLimit;

    private int rating;

}
