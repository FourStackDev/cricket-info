package org.fourstack.playcricket.battinginfo.helpers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.fourstack.playcricket.battinginfo.models.BattingInfo;
import org.fourstack.playcricket.battinginfo.models.PlayerBattingInfo;
import org.fourstack.playcricket.battinginfo.models.data.BattingInfoData;
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
	
	@Autowired
	private BattingExternalApiHelper externalApiHelper;

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
		if (externalApiHelper.checkIfPlayerExistsForPlayerId(playerInfo.getPlayerId())) {
			PlayerBattingInfoData playerDataModel = mappingHelper.mapBattingModelToDataBaseModel(playerInfo);
			
			playerDataModel = savePlayersBattingData(playerDataModel);
			playerInfo.setBattingInfoId(playerDataModel.getPalyerBattingInfoId());
		}
		return playerInfo;
	}
	
	public PlayerBattingInfoData savePlayersBattingData(PlayerBattingInfoData playerBattingData) {
		playerBattingData.getStatistics()
		                 .stream()
		                 .forEach(data -> battingInfoRepository.save(data));
		return playerBattingInfoRepository.save(playerBattingData);
	}
	
	public PlayerBattingInfo updateBattingInfoForPlayerBattingStatistics(BattingInfo battingInfo, String playerId) {
		// check whether the Player Exists for playerId
		if (externalApiHelper.checkIfPlayerExistsForPlayerId(playerId)) {
			// map the BattingInfo to DataBase model
			BattingInfoData currentData = mappingHelper.convertBattingInfoToData(battingInfo, playerId);

			// fetch the PlayerBattingInfo using playerId
			Optional<PlayerBattingInfoData> optionalPlayerBattingInfoData = playerBattingInfoRepository
					.findByPlayerId(playerId);
			if (optionalPlayerBattingInfoData.isPresent()) {
				PlayerBattingInfoData playerData = optionalPlayerBattingInfoData.get();

				// check whether the BattingInfo(Statistics) is already exists or not
				List<BattingInfoData> statistics = playerData.getStatistics();
				Optional<BattingInfoData> optionalExistingData = statistics.stream()
						.filter(data -> data.getBattingInfoId().equals(currentData.getBattingInfoId())).findAny();
				BattingInfoData existingData = optionalExistingData.orElse(null);
				if (existingData == null) {
					// if not exists update the current data as Existing Data
					statistics.add(currentData);
				} else {
					// if data exists update the new values
					updateBattingInfoData(existingData, currentData);
				}

				// Save the data to Database
				savePlayersBattingData(playerData);
				return mappingHelper.mapPlayerBattingDataModelToApiExposedModel(playerData);
			}
		}
		return null;
	}
	
	public void updateBattingInfoData(BattingInfoData existingData, BattingInfoData currentData) {
		if (currentData.getAverage() > 0)
			existingData.setAverage(currentData.getAverage());

		if (currentData.getBalls() > 0)
			existingData.setBalls(currentData.getBalls());

		if (currentData.getBattingInfoId() != null)
			existingData.setBattingInfoId(currentData.getBattingInfoId());

		if (currentData.getDucks() > 0)
			existingData.setDucks(currentData.getDucks());

		if (currentData.getFormat() != null)
			existingData.setFormat(currentData.getFormat());

		if (currentData.getFours() > 0)
			existingData.setFours(currentData.getFours());

		if (currentData.getHighest() > 0)
			existingData.setHighest(currentData.getHighest());

		if (currentData.getInnings() > 0)
			existingData.setInnings(currentData.getInnings());

		if (currentData.getMatches() > 0)
			existingData.setMatches(currentData.getMatches());

		if (currentData.getNoOfCenturies() > 0)
			existingData.setNoOfCenturies(currentData.getNoOfCenturies());

		if (currentData.getNoOfDoubleCenturies() > 0)
			existingData.setNoOfDoubleCenturies(currentData.getNoOfDoubleCenturies());

		if (currentData.getNoOfHalfCenturies() > 0)
			existingData.setNoOfHalfCenturies(currentData.getNoOfHalfCenturies());

		if (currentData.getNoOfQuadrupleCenturies() > 0)
			existingData.setNoOfQuadrupleCenturies(currentData.getNoOfQuadrupleCenturies());

		if (currentData.getNoOfTripleCenturies() > 0)
			existingData.setNoOfTripleCenturies(currentData.getNoOfTripleCenturies());

		if (currentData.getNumberOfNotOuts() > 0)
			existingData.setNumberOfNotOuts(currentData.getNumberOfNotOuts());

		if (currentData.getRuns() > 0)
			existingData.setRuns(currentData.getRuns());

		if (currentData.getSixes() > 0)
			existingData.setSixes(currentData.getSixes());

		if (currentData.getStrikeRate() > 0)
			existingData.setStrikeRate(currentData.getStrikeRate());
	}
}
