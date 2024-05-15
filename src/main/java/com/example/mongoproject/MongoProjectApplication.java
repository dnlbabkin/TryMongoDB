package com.example.mongoproject;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class MongoProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongoProjectApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(StudentRepository repository) {
		return args -> {
			Address address = new Address(
					"Dudeland",
					"st of dudes",
					"2281337"
			);
			String mail = "dude228@mail.com";
			Student student = new Student(
					"Dude",
					"Dudeson",
					mail,
					Gender.MALE,
					address,
					List.of("Dudes maths"),
					BigDecimal.TEN,
					LocalDateTime.now()
			);


			repository.findStudentByEmail(mail).ifPresentOrElse(s -> System.out.println(s + " already exists"), () -> {
				System.out.println("Inserting " + student);
				repository.insert(student);
			});
		};
	}
}
