package com.leviatan.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class LaBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(LaBackendApplication.class, args);
	}

}
