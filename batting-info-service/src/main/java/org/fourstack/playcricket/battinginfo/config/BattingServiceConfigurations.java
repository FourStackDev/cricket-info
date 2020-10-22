package org.fourstack.playcricket.battinginfo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BattingServiceConfigurations {
	
	@Autowired
	private BattingServiceProperties properties;
	
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setRequestFactory(requestFactory());
		return restTemplate;
	}
	
	@Bean
	public HttpComponentsClientHttpRequestFactory requestFactory() {
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		
		//set connectTimeOut and ReadTimeOut properties
		requestFactory.setConnectTimeout(properties.getTimeOut());
		requestFactory.setReadTimeout(properties.getTimeOut());
		return requestFactory;
	}
}
