package org.fourstack.playcricket.playerinfo.helpers;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.fourstack.playcricket.playerinfo.models.PlayerBasicInfo;
import org.fourstack.playcricket.playerinfo.models.PlayerProfileInfo;
import org.fourstack.playcricket.playerinfo.models.common.MultiMediaDocument;
import org.fourstack.playcricket.playerinfo.repositories.PlayerProfileInfoRepository;
import org.fourstack.playcricket.playerinfo.utility.PlayerUniqueIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class PlayerProfileInfoHelper {
	@Autowired
	private PlayerProfileInfoRepository playerInfoRepository;

	@Autowired
	private MultiMediaDocumentHelper multiMediaHelper;

	@Autowired
	private PlayerBasicInfoHelper playerbasicInfoHelper;

	@Autowired
	private PlayerUniqueIdGenerator uniqueIdGenerator;

	public List<PlayerProfileInfo> getAllPlayersProfiles() {
		return playerInfoRepository.findAll();
	}

	public Page<PlayerProfileInfo> getAllPlayersProfiles(Pageable pageable) {
		Page<PlayerProfileInfo> playerInfoPage = playerInfoRepository.findAll(pageable);
		return playerInfoPage;
	}
	
	public PlayerProfileInfo getPlayerProfileById(String playerId) {
		Optional<PlayerProfileInfo> optionalPlayer = playerInfoRepository.findById(playerId);
		if (optionalPlayer.isPresent())
			return optionalPlayer.get();
		
		throw new RuntimeException("Unable to find Player By Id: "+playerId);
	}

	public PlayerProfileInfo savePlayerProfile(PlayerProfileInfo playerProfile) {
		log.info("PlayerProfileInfoHelper.savePlayerProfile() - Start");
		try {
			long start = System.currentTimeMillis();
			PlayerBasicInfo basicInfo = playerProfile.getBasicInfo();
			CompletableFuture<String> generatedPlayerUniqueId = (basicInfo != null)
					? uniqueIdGenerator.generatePlayerUniqueId(basicInfo.getFirstName())
					: uniqueIdGenerator.generatePlayerUniqueId(null);

			CompletableFuture<PlayerBasicInfo> savedPlayerBasicInfo = null;
			if (basicInfo != null) {
				savedPlayerBasicInfo = playerbasicInfoHelper.savePlayerBasicInfo(basicInfo);
			}

			MultiMediaDocument imageInfo = playerProfile.getImageInfo();
			CompletableFuture<MultiMediaDocument> savedDocument = null;
			if (imageInfo != null) {
				savedDocument = multiMediaHelper.saveMultiMediaDocument(imageInfo);
			}

			if (savedPlayerBasicInfo != null)
				playerProfile.setBasicInfo(savedPlayerBasicInfo.get());
			if (savedDocument != null)
				playerProfile.setImageInfo(savedDocument.get());

			playerProfile.setPlayerId(generatedPlayerUniqueId.get());
			playerProfile = playerInfoRepository.save(playerProfile);

			long end = System.currentTimeMillis();
			log.info("Time Taken :: {}", end - start);
		} catch (Exception e) {
			log.error("PlayerProfileInfoHelper.savePlayerProfile() - Exception occurred => {}", e.getMessage());
		}
		log.info("PlayerProfileInfoHelper.savePlayerProfile() - End");
		return playerProfile;
	}
}
