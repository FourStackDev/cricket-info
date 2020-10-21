package org.fourstack.playcricket.playerinfo.config;

import java.util.concurrent.Executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class PlayerConfig {
	
	@Autowired
	private PlayerInfoProperties playerProperties;
	
	public Executor taskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(playerProperties.getCorePoolSize());
		executor.setMaxPoolSize(playerProperties.getMaxPoolSize());
		executor.setQueueCapacity(playerProperties.getQueueCapacity());
		executor.setThreadNamePrefix(playerProperties.getThreadNamePrefix());
		executor.initialize();
		return executor;
	}
	
}
