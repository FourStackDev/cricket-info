package org.fourstack.playcricket.rankinginfo.helpers;

import org.fourstack.playcricket.rankinginfo.configs.RankingServiceProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RankingExternalApiHelper {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private RankingServiceProperties properties;

	public Boolean checkIfPlayerExistsForId(String playerId) {
		log.info("RankingExternalApiHelper.checkIfPlayerExistsForId() - Start => {}", playerId);
		Boolean isPlayerExists = Boolean.FALSE;
		try {
			isPlayerExists = restTemplate.getForObject(properties.getUrlCheckIfPlayerExists(), Boolean.class, playerId);
		} catch (Exception e) {
			log.error("Exception occurred while Checking the Existence of Player : {} => {}", playerId, e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
		log.info("RankingExternalApiHelper.checkIfPlayerExistsForId() - End => {}", playerId);

		return isPlayerExists;
	}
}
