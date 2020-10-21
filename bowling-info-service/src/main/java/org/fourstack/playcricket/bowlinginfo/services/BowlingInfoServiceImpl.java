package org.fourstack.playcricket.bowlinginfo.services;

import java.util.List;

import org.fourstack.playcricket.bowlinginfo.helpers.BowlingInfoServiceHelper;
import org.fourstack.playcricket.bowlinginfo.models.PlayerBowlingInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BowlingInfoServiceImpl implements BowlingInfoService {

	@Autowired
	private BowlingInfoServiceHelper bowlingHelper;

	@Override
	public List<PlayerBowlingInfo> getAllPlayersBowlingStatistics() {
		return bowlingHelper.getAllPlayersBowlingStatistics();
	}

	@Override
	public Page<PlayerBowlingInfo> getPlayersBowlingStatisticsPage(Pageable pageable) {
		return bowlingHelper.getPlayersBowlingStatistics(pageable);
	}

	@Override
	public PlayerBowlingInfo getPlayerBowlingStatisticsById(String id) {
		return bowlingHelper.getPlayerBowlingStatisticsById(id);
	}

	@Override
	public PlayerBowlingInfo getPlayerBowlingStatisticsByPlayerId(String playerId) {
		return bowlingHelper.getPlayerBowlingStatisticsByPlayerId(playerId);
	}

	@Override
	public PlayerBowlingInfo savePlayerBowlingStatistics(PlayerBowlingInfo bowlingInfo) {
		return bowlingHelper.savePlayersBowlingStatistics(bowlingInfo);
	}
}
