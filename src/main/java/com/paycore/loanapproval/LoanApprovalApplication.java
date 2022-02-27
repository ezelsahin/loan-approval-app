package com.paycore.loanapproval;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Loan Approval API", description = "Requesting maximum loan limit service"))
public class LoanApprovalApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoanApprovalApplication.class, args);
	}

}
