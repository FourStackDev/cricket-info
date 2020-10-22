package org.fourstack.playcricket.rankinginfo.helpers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.fourstack.playcricket.rankinginfo.models.IccRanking;
import org.fourstack.playcricket.rankinginfo.models.data.IccRankingData;
import org.fourstack.playcricket.rankinginfo.models.data.RankingInfoData;
import org.fourstack.playcricket.rankinginfo.repositories.IccRankingRepository;
import org.fourstack.playcricket.rankinginfo.repositories.RankingInfoDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RankingInfoServiceHelper {

	@Autowired
	private IccRankingRepository rankingRepository;

	@Autowired
	private RankingInfoDataRepository rankingDataRepository;

	@Autowired
	private RankingDataModelMappingHelper modelMappingHelper;
	
	@Autowired
	private RankingExternalApiHelper externalApiHelper;

	public List<IccRanking> getIccRankingList() {
		log.debug("RankingInfoServiceHelper.getIccRankingList() - Start");

		List<IccRankingData> rankingDataList = rankingRepository.findAll();

		if (rankingDataList == null || rankingDataList.size() == 0)
			throw new RuntimeException("NO DATA FOUND");

		List<IccRanking> rankingList = rankingDataList.stream()
				.map(rankingData -> modelMappingHelper.mapIccDataModelToApiExposedModel(rankingData))
				.collect(Collectors.toList());
		log.debug("RankingInfoServiceHelper.getIccRankingList() - End");
		return rankingList;
	}

	public Page<IccRanking> getIccRankingsPage(Pageable pageable) {
		log.debug("RankingInfoServiceHelper.getIccRankingsPage() - Start");
		Page<IccRankingData> rankingDataPage = rankingRepository.findAll(pageable);

		if (rankingDataPage == null || !rankingDataPage.hasContent())
			throw new RuntimeException("NO DATA FOUND");

		Page<IccRanking> iccRankingPage = modelMappingHelper.convertIccRankingDataPageToIccRankingPage(rankingDataPage,
				pageable);

		log.debug("RankingInfoServiceHelper.getIccRankingsPage() - End");
		return iccRankingPage;
	}

	public IccRanking getIccRankingById(String rankingId) {
		Optional<IccRankingData> optionalRankingData = rankingRepository.findById(rankingId);

		IccRanking iccRanking = fetchAndValidateIccRanking(rankingId, optionalRankingData);

		return iccRanking;
	}

	public IccRanking getIccRankingByPlayerId(String playerId) {
		Optional<IccRankingData> optionalRankingData = rankingRepository.findByPlayerId(playerId);

		IccRanking iccRanking = fetchAndValidateIccRanking(playerId, optionalRankingData);

		return iccRanking;
	}

	private IccRanking fetchAndValidateIccRanking(String id, Optional<IccRankingData> optionalRankingData) {
		IccRanking iccRanking = null;
		if (optionalRankingData.isPresent()) {
			iccRanking = modelMappingHelper.mapIccDataModelToApiExposedModel(optionalRankingData.get());
		}

		if (iccRanking == null)
			throw new RuntimeException("No Data found for the Id : " + id);
		return iccRanking;
	}

	public IccRanking saveIccRanking(IccRanking ranking) {
		//if (externalApiHelper.checkIfPlayerExistsForId(ranking.getPlayerId())) {
			IccRankingData rankingDataModel = modelMappingHelper.mapIccRankingModelToDataModel(ranking);

			/**
			 * Save each ranking in the List
			 */
			List<RankingInfoData> rankingInfoDataList = rankingDataModel.getRankings().stream()
					.map(individualRanking -> rankingDataRepository.save(individualRanking)).collect(Collectors.toList());

			rankingDataModel.setRankings(rankingInfoDataList);
			rankingRepository.save(rankingDataModel);

			ranking.setRankingId(rankingDataModel.getRankingId());
		//}		
		return ranking;
	}
}
