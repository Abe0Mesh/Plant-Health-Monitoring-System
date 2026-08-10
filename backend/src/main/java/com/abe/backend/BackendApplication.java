package com.abe.backend;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.*;

import com.abe.backend.databaseman.*;
import com.abe.backend.measurement.*;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
		
	}

	@Bean
	public CommandLineRunner run(Measurement measurment) {
		return (args) -> {
			measurment.readMeasurements();
		};
	}

}
