package org.fourstack.playcricket.playerinfo.services;

import java.util.List;

import org.fourstack.playcricket.playerinfo.models.PlayerProfileInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PlayerInfoService {
	
	public List<PlayerProfileInfo> fetchAllPlayersInfo();
	
	public PlayerProfileInfo fetchPlayerById(String playerId);
	
	public Page<PlayerProfileInfo> fetchPlayersProfiles(Pageable pageable);
	
	public PlayerProfileInfo savePlayerProfile(PlayerProfileInfo playerProfile);
	
	public boolean isPlayerProfileExistsOrNot(String playerId);
}
