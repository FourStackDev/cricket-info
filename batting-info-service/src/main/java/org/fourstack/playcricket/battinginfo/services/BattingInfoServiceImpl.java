package org.fourstack.playcricket.battinginfo.services;

import java.util.List;

import org.fourstack.playcricket.battinginfo.helpers.BattingInfoServiceHelper;
import org.fourstack.playcricket.battinginfo.helpers.BattingInfoValidationHelper;
import org.fourstack.playcricket.battinginfo.models.PlayerBattingInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BattingInfoServiceImpl implements BattingInfoService {
	
	@Autowired
	private BattingInfoServiceHelper battingServiceHelper;
	
	@Autowired
	private BattingInfoValidationHelper validationHelper;

	@Override
	public List<PlayerBattingInfo> getAllPlayersBattingStatistics() {
		return battingServiceHelper.getAllPlayersBattingStatistics();
	}

	@Override
	public Page<PlayerBattingInfo> getPlayersBattingStatistics(Pageable pageable) {
		return battingServiceHelper.getPlayersBattingStatistics(pageable);
	}

	@Override
	public PlayerBattingInfo getPlayersBattingStatisticsById(String id) {
		return battingServiceHelper.getPlayersBattingStatisticsById(id);
	}

	@Override
	public PlayerBattingInfo getPlayersBattingStatisticsByPlayerId(String playerId) {
		return battingServiceHelper.getPlayersBattingStatisticsByPlayerId(playerId);
	}

	@Override
	public PlayerBattingInfo savePlayersBattingStatistics(PlayerBattingInfo playerInfo) {
		validationHelper.validatePlayerBattingInfo(playerInfo);
		return battingServiceHelper.savePlayersBattingStatistics(playerInfo);
	}
}
