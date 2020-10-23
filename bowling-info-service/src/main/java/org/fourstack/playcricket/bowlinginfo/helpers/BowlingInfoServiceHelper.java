package org.fourstack.playcricket.bowlinginfo.helpers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.fourstack.playcricket.bowlinginfo.models.BowlingInfo;
import org.fourstack.playcricket.bowlinginfo.models.PlayerBowlingInfo;
import org.fourstack.playcricket.bowlinginfo.models.data.BowlingInfoData;
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

	@Autowired
	private BowlingExternalApiHelper externalApiHelper;

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
		if (externalApiHelper.checkIfPlayerExistsForPlayerId(bowlingInfo.getPlayerId())) {
			PlayerBowlingInfoData bowlingData = mappingHelper.mapBowlingInfoToDatabaseModel(bowlingInfo);
			saveBowlingInfoData(bowlingData);
			bowlingInfo.setBowlingInfoId(bowlingData.getPlayerBowlingInfoId());
		}
		return bowlingInfo;
	}

	private void saveBowlingInfoData(PlayerBowlingInfoData bowlingData) {
		bowlingData.getStatistics().stream().forEach(data -> bowlingRepository.save(data));
		playerBowlingRepository.save(bowlingData);
	}

	public PlayerBowlingInfo patchBowlingStatisticsToPlayer(String playerId, BowlingInfo bowlingInfo) {
		// check if the Player Exists for playerId
		if (externalApiHelper.checkIfPlayerExistsForPlayerId(playerId)) {
			// map the BowlingInfo to BowlingInfoData
			BowlingInfoData dataFromApi = mappingHelper.mapBowlingInfoToData(playerId, bowlingInfo);

			// fetch the PlayerBowlingData from Repository using playerId
			Optional<PlayerBowlingInfoData> optionalBowlingData = playerBowlingRepository.findByPlayerId(playerId);
			if (optionalBowlingData.isPresent()) {
				PlayerBowlingInfoData existingBowlingInfoData = optionalBowlingData.get();

				// check whether the BowlingInfo(Statistics) is already exists or not
				Optional<BowlingInfoData> optionalExistingData = existingBowlingInfoData.getStatistics().stream()
						.filter(data -> data.getBowlingInfoId().equals(dataFromApi.getBowlingInfoId())).findAny();
				BowlingInfoData existingData = optionalExistingData.orElse(null);
				if (existingData == null) {
					// if not exists update the dataFromApi as Existing Data
					existingBowlingInfoData.getStatistics().add(dataFromApi);
				} else {
					// if data exists update the new values
					updateBowlingInfoData(existingData, dataFromApi);
				}

				// save the data to database
				saveBowlingInfoData(existingBowlingInfoData);
				return mappingHelper.mapBowlingDatabaseModelToApiExposedModel(existingBowlingInfoData);
			}
		}

		return null;
	}

	private void updateBowlingInfoData(BowlingInfoData existingData, BowlingInfoData dataFromApi) {
		if (dataFromApi.getAverage() > 0)
			existingData.setAverage(dataFromApi.getAverage());
		
		if (dataFromApi.getBalls() > 0)
			existingData.setBalls(dataFromApi.getBalls());
		
		if (dataFromApi.getBestBowlingInInnings() != null)
			existingData.setBestBowlingInInnings(dataFromApi.getBestBowlingInInnings());
		
		if (dataFromApi.getBestBowlingInMatch() != null)
			existingData.setBestBowlingInMatch(dataFromApi.getBestBowlingInMatch());
		
		if (dataFromApi.getEconomy() > 0)
			existingData.setEconomy(dataFromApi.getEconomy());
		
		if (dataFromApi.getFiveWicketHaul() > 0)
			existingData.setFiveWicketHaul(dataFromApi.getFiveWicketHaul());
		
		if (dataFromApi.getFormat() != null)
			existingData.setFormat(dataFromApi.getFormat());
		
		if (dataFromApi.getFourWicketHaul() > 0)
			existingData.setFourWicketHaul(dataFromApi.getFourWicketHaul());

		if (dataFromApi.getInnings() > 0)
			existingData.setInnings(dataFromApi.getInnings());

		if (dataFromApi.getMaidens() > 0)
			existingData.setMaidens(dataFromApi.getMaidens());

		if (dataFromApi.getMatches() > 0)
			existingData.setMatches(dataFromApi.getMatches());

		if (dataFromApi.getRuns() > 0)
			existingData.setRuns(dataFromApi.getRuns());

		if (dataFromApi.getStrikeRate() > 0)
			existingData.setStrikeRate(dataFromApi.getStrikeRate());

		if (dataFromApi.getTenWicketHaul() > 0)
			existingData.setTenWicketHaul(dataFromApi.getTenWicketHaul());

		if (dataFromApi.getWickets() > 0)
			existingData.setWickets(dataFromApi.getWickets());
	}
}
