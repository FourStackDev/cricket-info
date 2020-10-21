package org.fourstack.playcricket.battinginfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BattingInfoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BattingInfoServiceApplication.class, args);
	}

}
