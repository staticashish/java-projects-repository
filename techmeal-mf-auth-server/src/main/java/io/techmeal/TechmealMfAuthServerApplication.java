package io.techmeal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class TechmealMfAuthServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechmealMfAuthServerApplication.class, args);
	}
}
