package org.fourstack.playcricket.battinginfo.services;

import java.util.List;

import org.fourstack.playcricket.battinginfo.models.PlayerBattingInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BattingInfoService {

	public List<PlayerBattingInfo> getAllPlayersBattingStatistics();

	public Page<PlayerBattingInfo> getPlayersBattingStatistics(Pageable pageable);

	public PlayerBattingInfo getPlayersBattingStatisticsById(String id);

	public PlayerBattingInfo getPlayersBattingStatisticsByPlayerId(String playerId);

	public PlayerBattingInfo savePlayersBattingStatistics(PlayerBattingInfo playerInfo);
}
