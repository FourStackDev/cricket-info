package org.fourstack.playcricket.rankinginfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class RankingInfoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RankingInfoServiceApplication.class, args);
	}

}
