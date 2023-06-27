package com.example.taskcrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class TaskCrudApplication {

	public static void main(String[] args) {
		String jsonSchema = "{\"taskName\": \"\", \"taskDescription\": \"\", \"startDate\": \"\", \"endDate\": \"\", \"taskPriority\": false, \"taskStatus\": false, \"repeat\": false}";
		String incomingJson = "{\"taskName\": \"Task 1\", \"taskDescription\": \"Description\", \"startDate\": \"2022-01-01\", \"endDate\": \"2022-01-05\", \"taskPriority\": true, \"taskStatus\": false, \"repeat\": false}";
		try {
			JsonValidator validator = new JsonValidator(jsonSchema);
			boolean isValid = validator.validate(incomingJson);

			if (isValid) {
				System.out.println("JSON structure is valid");
				// Perform further processing
			} else {
				System.out.println("JSON structure is invalid");
				// Handle the validation error
			}
		} catch (IOException e) {
			System.out.println("Error occurred during JSON validation: " + e.getMessage());
			// Handle the exception
		}

		SpringApplication.run(TaskCrudApplication.class, args);
	}

}
