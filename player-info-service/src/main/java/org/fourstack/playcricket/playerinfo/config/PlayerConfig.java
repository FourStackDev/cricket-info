package org.fourstack.playcricket.playerinfo.config;

import java.util.concurrent.Executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

@Configuration
public class PlayerConfig {
	
	@Autowired
	private PlayerInfoProperties playerProperties;
	
	@Bean(name = "AsyncThreadPoolExecutor")
	public Executor taskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(playerProperties.getCorePoolSize());
		executor.setMaxPoolSize(playerProperties.getMaxPoolSize());
		executor.setQueueCapacity(playerProperties.getQueueCapacity());
		executor.setThreadNamePrefix(playerProperties.getThreadNamePrefix());
		executor.initialize();
		return executor;
	}
	
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
		
		//set connectTimeOut and ReadTimeOut
		requestFactory.setConnectTimeout(playerProperties.getTimeOut());
		requestFactory.setReadTimeout(playerProperties.getTimeOut());
		return requestFactory;
	}
	
}
