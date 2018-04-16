package io.techmeal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EnableEurekaClient
@SpringBootApplication
public class TechmealMfServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechmealMfServiceApplication.class, args);
	}
}
