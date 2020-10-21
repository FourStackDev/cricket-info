package org.fourstack.playcricket.battinginfo.helpers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.fourstack.playcricket.battinginfo.models.PlayerBattingInfo;
import org.fourstack.playcricket.battinginfo.models.data.PlayerBattingInfoData;
import org.fourstack.playcricket.battinginfo.repositories.BattingInfoDataRepository;
import org.fourstack.playcricket.battinginfo.repositories.PlayerBattingInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class BattingInfoServiceHelper {

	@Autowired
	private BattingInfoDataRepository battingInfoRepository;

	@Autowired
	private PlayerBattingInfoRepository playerBattingInfoRepository;

	@Autowired
	private BattingDataModelMappingHelper mappingHelper;

	public List<PlayerBattingInfo> getAllPlayersBattingStatistics() {
		List<PlayerBattingInfoData> battingInfoDataList = playerBattingInfoRepository.findAll();

		if (battingInfoDataList == null || battingInfoDataList.size() == 0)
			throw new RuntimeException("Unable to find the data");

		List<PlayerBattingInfo> playerBattingInfoList = battingInfoDataList.stream()
				.map(battingData -> mappingHelper.mapPlayerBattingDataModelToApiExposedModel(battingData))
				.collect(Collectors.toList());

		return playerBattingInfoList;
	}

	public Page<PlayerBattingInfo> getPlayersBattingStatistics(Pageable pageable) {
		Page<PlayerBattingInfoData> battingDataPage = playerBattingInfoRepository.findAll(pageable);

		if (!battingDataPage.hasContent())
			throw new RuntimeException("Unable to find the data");

		return mappingHelper.convertPlayerBattingDataPageToPlayerBattingInfoPage(battingDataPage, pageable);
	}

	public PlayerBattingInfo getPlayersBattingStatisticsById(String id) {
		Optional<PlayerBattingInfoData> optionalPalyerInfo = playerBattingInfoRepository.findById(id);

		return extract(id, optionalPalyerInfo);
	}

	private PlayerBattingInfo extract(String id, Optional<PlayerBattingInfoData> optionalPalyerInfo) {
		if (optionalPalyerInfo.isPresent()) {
			PlayerBattingInfoData playerBattingInfoData = optionalPalyerInfo.get();
			return mappingHelper.mapPlayerBattingDataModelToApiExposedModel(playerBattingInfoData);
		}

		throw new RuntimeException("No Data found for the id: " + id);
	}

	public PlayerBattingInfo getPlayersBattingStatisticsByPlayerId(String playerId) {
		Optional<PlayerBattingInfoData> optionalPalyerInfo = playerBattingInfoRepository.findByPlayerId(playerId);

		return extract(playerId, optionalPalyerInfo);
	}

	public PlayerBattingInfo savePlayersBattingStatistics(PlayerBattingInfo playerInfo) {

		PlayerBattingInfoData playerDataModel = mappingHelper.mapBattingModelToDataBaseModel(playerInfo);

		playerDataModel.getStatistics().forEach(battingData -> battingInfoRepository.save(battingData));
		playerBattingInfoRepository.save(playerDataModel);

		playerInfo.setBattingInfoId(playerDataModel.getPalyerBattingInfoId());
		return playerInfo;
	}
}
