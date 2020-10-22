package org.fourstack.playcricket.battinginfo.helpers;

import org.fourstack.playcricket.battinginfo.config.BattingServiceProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class BattingExternalApiHelper {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private BattingServiceProperties properties;
	
	public Boolean checkIfPlayerExistsForPlayerId(String playerId) {
		log.info("BattingExternalApiHelper.checkIfPlayerExistsForPlayerId() - Start => {}", playerId);
		Boolean isExists = Boolean.FALSE;
		try {
			isExists = restTemplate.getForObject(properties.getUrlCheckIfPlayerExistsForPlayerId(), Boolean.class, playerId);
		} catch (Exception e) {
			log.error("Exception occurred while Checking player Exists or not - {} => {}", playerId, e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
		log.info("BattingExternalApiHelper.checkIfPlayerExistsForPlayerId() - End => {}", playerId);
		
		return isExists;
	}
}
