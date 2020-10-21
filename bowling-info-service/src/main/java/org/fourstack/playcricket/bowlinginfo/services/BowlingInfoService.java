package org.fourstack.playcricket.bowlinginfo.services;

import java.util.List;

import org.fourstack.playcricket.bowlinginfo.models.PlayerBowlingInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BowlingInfoService {
	
	public List<PlayerBowlingInfo> getAllPlayersBowlingStatistics();
	
	public Page<PlayerBowlingInfo> getPlayersBowlingStatisticsPage(Pageable pageable);
	
	public PlayerBowlingInfo getPlayerBowlingStatisticsById(String id);
	
	public PlayerBowlingInfo getPlayerBowlingStatisticsByPlayerId(String playerId);
	
	public PlayerBowlingInfo savePlayerBowlingStatistics(PlayerBowlingInfo bowlingInfo);

}
