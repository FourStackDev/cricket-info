package org.fourstack.playcricket.bowlinginfo.helpers;

import org.fourstack.playcricket.bowlinginfo.config.BowlingServiceProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class BowlingExternalApiHelper {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private BowlingServiceProperties properties;

	public Boolean checkIfPlayerExistsForPlayerId(String playerId) {
		log.info("BowlingExternalApiHelper.checkIfPlayerExistsForPlayerId() - Start => {}", playerId);
		Boolean isExists = Boolean.FALSE;
		try {
			isExists = restTemplate.getForObject(properties.getUrlCheckIfPlayerExistsForPlayerId(), Boolean.class, playerId);
		} catch (Exception e) {
			log.error("Exception occurred while Checking the Existence of Player : {} => {}", playerId, e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
		log.info("BowlingExternalApiHelper.checkIfPlayerExistsForPlayerId() - End => {}", playerId);
		return isExists;
	}

}
