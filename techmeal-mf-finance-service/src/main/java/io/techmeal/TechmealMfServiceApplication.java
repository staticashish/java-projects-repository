package io.techmeal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class TechmealMfServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechmealMfServiceApplication.class, args);
	}
}
