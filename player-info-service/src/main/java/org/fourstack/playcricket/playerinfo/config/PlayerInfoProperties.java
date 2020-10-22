package org.fourstack.playcricket.playerinfo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class PlayerInfoProperties {
	/**
	 * Start >>>> Thread Pool Constants
	 */
	@Value("${CORE_POOL_SIZE}")
	private int corePoolSize;

	@Value("${MAX_POOL_SIZE}")
	private int maxPoolSize;

	@Value("${QUEUE_CAPACITY}")
	private int queueCapacity;

	@Value("${THREAD_NAME_PREFIX}")
	private String threadNamePrefix;

	/**
	 * End >>>> Thread Pool Constants
	 */
	
	@Value("${PATCH_METHOD_TIMEOUT}")
	private int timeOut;
}
