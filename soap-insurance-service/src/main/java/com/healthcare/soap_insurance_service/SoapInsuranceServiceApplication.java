package com.healthcare.soap_insurance_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.healthcare")
public class SoapInsuranceServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoapInsuranceServiceApplication.class, args);
	}

}
