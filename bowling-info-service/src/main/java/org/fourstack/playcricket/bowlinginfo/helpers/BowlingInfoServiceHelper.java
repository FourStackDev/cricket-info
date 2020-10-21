package org.fourstack.playcricket.bowlinginfo.helpers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.fourstack.playcricket.bowlinginfo.models.PlayerBowlingInfo;
import org.fourstack.playcricket.bowlinginfo.models.data.PlayerBowlingInfoData;
import org.fourstack.playcricket.bowlinginfo.repositories.BowlingInfoDataRepository;
import org.fourstack.playcricket.bowlinginfo.repositories.PlayerBowlingInfoDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class BowlingInfoServiceHelper {

	@Autowired
	private BowlingInfoDataRepository bowlingRepository;

	@Autowired
	private PlayerBowlingInfoDataRepository playerBowlingRepository;

	@Autowired
	private BowlingDataModelMappingHelper mappingHelper;

	public List<PlayerBowlingInfo> getAllPlayersBowlingStatistics() {
		List<PlayerBowlingInfoData> dataList = playerBowlingRepository.findAll();

		if (dataList == null || dataList.size() == 0)
			throw new RuntimeException("No Data Found");

		return dataList.stream().map(data -> mappingHelper.mapBowlingDatabaseModelToApiExposedModel(data))
				.collect(Collectors.toList());
	}

	public Page<PlayerBowlingInfo> getPlayersBowlingStatistics(Pageable pageable) {
		Page<PlayerBowlingInfoData> bowlingPage = playerBowlingRepository.findAll(pageable);

		if (bowlingPage == null || !bowlingPage.hasContent())
			throw new RuntimeException("No Data Found");

		return mappingHelper.convertBowlingDataPageToBowlingInfoPage(bowlingPage, pageable);
	}

	public PlayerBowlingInfo getPlayerBowlingStatisticsById(String id) {
		Optional<PlayerBowlingInfoData> optionalStatistics = playerBowlingRepository.findById(id);

		return extractBowlingInfo(id, optionalStatistics);
	}

	private PlayerBowlingInfo extractBowlingInfo(String id, Optional<PlayerBowlingInfoData> optionalStatistics) {
		if (optionalStatistics.isPresent()) {
			return mappingHelper.mapBowlingDatabaseModelToApiExposedModel(optionalStatistics.get());
		}

		throw new RuntimeException("Unable to find the Info: " + id);
	}

	public PlayerBowlingInfo getPlayerBowlingStatisticsByPlayerId(String playerId) {
		Optional<PlayerBowlingInfoData> optionalStatistics = playerBowlingRepository.findByPlayerId(playerId);
		return extractBowlingInfo(playerId, optionalStatistics);
	}

	public PlayerBowlingInfo savePlayersBowlingStatistics(PlayerBowlingInfo bowlingInfo) {
		PlayerBowlingInfoData bowlingData = mappingHelper.mapBowlingInfoToDatabaseModel(bowlingInfo);

		bowlingData.getStatistics().stream().forEach(data -> bowlingRepository.save(data));
		playerBowlingRepository.save(bowlingData);
		
		bowlingInfo.setBowlingInfoId(bowlingData.getPlayerBowlingInfoId());
		return bowlingInfo;

	}
}
