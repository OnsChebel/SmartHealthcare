package com.healthcare.rest_patient_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.healthcare")
public class RestPatientServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(RestPatientServiceApplication.class, args);
	}
}
