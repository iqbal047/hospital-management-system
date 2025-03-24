package com.iqbal.hospitalapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class HospitalManagementSystemApplication {

	public static void main(String[] args) {
	ConfigurableApplicationContext iqbal= SpringApplication.run(HospitalManagementSystemApplication.class, args);


		System.out.println("Hospital is working");
	}

}
