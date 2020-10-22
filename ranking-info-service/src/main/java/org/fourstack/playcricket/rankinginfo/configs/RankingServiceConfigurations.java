package org.fourstack.playcricket.rankinginfo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RankingServiceConfigurations {

	@Autowired
	private RankingServiceProperties properties;

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setRequestFactory(requestFactory());
		return  restTemplate;
	}

	/**
	 * RequestFactory required for RestTemplate while doing the PATCH calls.
	 * 
	 * @return HttpComponentsClientHttpRequestFactory
	 */
	@Bean
	public HttpComponentsClientHttpRequestFactory requestFactory() {
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		// configure the connectTimeOut and ReadTimeOut
		requestFactory.setConnectTimeout(properties.getTimeOut());
		requestFactory.setReadTimeout(properties.getTimeOut());

		return requestFactory;
	}
}
