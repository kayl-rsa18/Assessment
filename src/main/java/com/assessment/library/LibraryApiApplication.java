package com.assessment.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;


@SpringBootApplication
public class LibraryApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryApiApplication.class, args);
	}

	@Bean
	public OpenAPI springOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("Library Book API")
						.description("Library Book API Operations")
						.version("v0.0.1")
						.license(new License().name("Apache 2.0").url("http://springdoc.org")));
	}

}
