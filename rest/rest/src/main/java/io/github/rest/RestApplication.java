package io.github.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableSwagger2
public class RestApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApplication.class, args);
	}

//	@Bean
//	public Docket productApi() {
//		return new Docket(DocumentationType.SWAGGER_2).select()
//				.apis(RequestHandlerSelectors.basePackage("io.github.rest")).build();
//	}

}
