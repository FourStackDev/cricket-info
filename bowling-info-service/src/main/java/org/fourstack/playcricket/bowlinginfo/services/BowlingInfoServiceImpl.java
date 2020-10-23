package org.fourstack.playcricket.bowlinginfo.services;

import java.util.List;

import org.fourstack.playcricket.bowlinginfo.helpers.BowlingInfoServiceHelper;
import org.fourstack.playcricket.bowlinginfo.helpers.BowlingInfoValidationHelper;
import org.fourstack.playcricket.bowlinginfo.models.BowlingInfo;
import org.fourstack.playcricket.bowlinginfo.models.PlayerBowlingInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BowlingInfoServiceImpl implements BowlingInfoService {

	@Autowired
	private BowlingInfoServiceHelper bowlingHelper;
	
	@Autowired
	private BowlingInfoValidationHelper validationHelper;

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
		validationHelper.validatePlayerBowlingInfo(bowlingInfo);
		return bowlingHelper.savePlayersBowlingStatistics(bowlingInfo);
	}

	@Override
	public PlayerBowlingInfo patchBowlingStatisticsToPlayer(String playerId, BowlingInfo bowlingInfo) {
		validationHelper.validatePlayerId(playerId);
		validationHelper.validateCricketFormat(bowlingInfo.getFormat());
		return bowlingHelper.patchBowlingStatisticsToPlayer(playerId, bowlingInfo);
	}
}
