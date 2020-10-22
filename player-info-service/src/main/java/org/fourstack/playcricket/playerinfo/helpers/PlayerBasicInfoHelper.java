package org.fourstack.playcricket.playerinfo.helpers;

import java.util.concurrent.CompletableFuture;

import org.fourstack.playcricket.playerinfo.models.PlayerBasicInfo;
import org.fourstack.playcricket.playerinfo.repositories.PlayerBasicInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class PlayerBasicInfoHelper {

	@Autowired
	private PlayerBasicInfoRepository repository;

	@Async("AsyncThreadPoolExecutor")
	public CompletableFuture<PlayerBasicInfo> savePlayerBasicInfo(PlayerBasicInfo playerInfo) {
		PlayerBasicInfo savedPlayerBasicInfo = repository.save(playerInfo);
		return CompletableFuture.completedFuture(savedPlayerBasicInfo);
	}

}
