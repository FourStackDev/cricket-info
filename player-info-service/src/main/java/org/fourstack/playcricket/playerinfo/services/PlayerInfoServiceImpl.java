package org.fourstack.playcricket.playerinfo.services;

import java.util.List;

import org.fourstack.playcricket.playerinfo.helpers.PlayerProfileInfoHelper;
import org.fourstack.playcricket.playerinfo.models.PlayerProfileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PlayerInfoServiceImpl implements PlayerInfoService {
	
	@Autowired
	private PlayerProfileInfoHelper playerInfoHelper;

	@Override
	public List<PlayerProfileInfo> fetchAllPlayersInfo() {
		return playerInfoHelper.getAllPlayersProfiles();
	}

	@Override
	public PlayerProfileInfo fetchPlayerById(String playerId) {
		return playerInfoHelper.getPlayerProfileById(playerId);
	}
	
	@Override
	public Page<PlayerProfileInfo> fetchPlayersProfiles(Pageable pageable) {
		return playerInfoHelper.getAllPlayersProfiles(pageable);
	}

	@Override
	public PlayerProfileInfo savePlayerProfile(PlayerProfileInfo playerProfile) {
		return playerInfoHelper.savePlayerProfile(playerProfile);
	}

	@Override
	public boolean isPlayerProfileExistsOrNot(String playerId) {
		PlayerProfileInfo fetchPlayerById = fetchPlayerById(playerId);
		return fetchPlayerById != null ? Boolean.TRUE : Boolean.FALSE;
	}

}
