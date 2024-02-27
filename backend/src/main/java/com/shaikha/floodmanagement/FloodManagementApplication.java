package com.shaikha.floodmanagement;

import com.shaikha.floodmanagement.cofig.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class FloodManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(FloodManagementApplication.class, args);
	}

}
