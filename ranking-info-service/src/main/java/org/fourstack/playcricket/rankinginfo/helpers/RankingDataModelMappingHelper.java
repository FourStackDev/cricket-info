package org.fourstack.playcricket.rankinginfo.helpers;

import static org.fourstack.playcricket.rankinginfo.constants.RankingConstants.FIELD_BAT;
import static org.fourstack.playcricket.rankinginfo.constants.RankingConstants.FIELD_BOWL;
import static org.fourstack.playcricket.rankinginfo.constants.RankingConstants.FILED_ALLROUND;
import static org.fourstack.playcricket.rankinginfo.constants.RankingConstants.ODI_AREA;
import static org.fourstack.playcricket.rankinginfo.constants.RankingConstants.RANK_PREFIX;
import static org.fourstack.playcricket.rankinginfo.constants.RankingConstants.T20_AREA;
import static org.fourstack.playcricket.rankinginfo.constants.RankingConstants.TEST_AREA;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.fourstack.playcricket.rankinginfo.codetype.RankingArea;
import org.fourstack.playcricket.rankinginfo.codetype.RankingField;
import org.fourstack.playcricket.rankinginfo.models.IccRanking;
import org.fourstack.playcricket.rankinginfo.models.RankingInfo;
import org.fourstack.playcricket.rankinginfo.models.RankingStatus;
import org.fourstack.playcricket.rankinginfo.models.data.IccRankingData;
import org.fourstack.playcricket.rankinginfo.models.data.RankingInfoData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class RankingDataModelMappingHelper {

	/**
	 * Method to map the {@link IccRanking} - User Exposed Object To
	 * {@link IccRankingData} - DataBase Stored Object
	 * 
	 * @param iccRanking {@link IccRanking} - User Exposed Object
	 * @return {@link IccRankingData} - Converted Database Stored Object
	 */
	public IccRankingData mapIccRankingModelToDataModel(IccRanking iccRanking) {
		IccRankingData rankingData = new IccRankingData();
		rankingData.setRankingId(getRankingId(iccRanking.getPlayerId()));
		rankingData.setPlayerId(iccRanking.getPlayerId());
		rankingData.setRankings(extractRankings(iccRanking.getRankingInfo(), rankingData.getRankingId()));

		return rankingData;
	}

	private List<RankingInfoData> extractRankings(List<RankingInfo> rankingInfoList, String rankingId) {

		List<RankingInfoData> rankingInfoDataList = new ArrayList<>();
		rankingInfoList.stream().forEach(rankingInfo -> {

			List<RankingStatus> rankingStatusList = rankingInfo.getStatus();

			rankingStatusList.stream().forEach(rankingStatus -> {
				RankingInfoData rankingInfoData = new RankingInfoData();
				rankingInfoData.setRankingInfoId(
						rankingInfo.getFormat().name().concat(rankingId).concat(rankingStatus.getField().name()));

				rankingInfoData.setArea(rankingInfo.getFormat().name());
				rankingInfoData.setField(rankingStatus.getField().name());
				rankingInfoData.setBestRanking(rankingStatus.getBestRanking());
				rankingInfoData.setCurrentRanking(rankingStatus.getCurrentRanking());

				rankingInfoDataList.add(rankingInfoData);
			});
		});

		return rankingInfoDataList;
	}

	private String getRankingId(String playerId) {
		return RANK_PREFIX.concat(playerId);
	}

	/**
	 * Method to map {@link IccRankingData} - Database Stored Object To
	 * {@link IccRanking} - User Exposed Object
	 * 
	 * @param iccRankingData {@link IccRankingData} - DataBase Stored Object
	 * @return {@link IccRanking} - Converted User Exposed Object
	 */
	public IccRanking mapIccDataModelToApiExposedModel(IccRankingData iccRankingData) {
		IccRanking ranking = new IccRanking();
		ranking.setRankingId(iccRankingData.getRankingId());
		ranking.setPlayerId(iccRankingData.getPlayerId());

		List<RankingInfo> rankingInfoList = new ArrayList<>();
		ranking.setRankingInfo(rankingInfoList);

		Map<RankingArea, List<RankingStatus>> areaStatusMap = new HashMap<>();
		List<RankingStatus> testRankingStatus = new ArrayList<>();
		List<RankingStatus> odiRankingStatus = new ArrayList<>();
		List<RankingStatus> t20RankingStatus = new ArrayList<>();

		iccRankingData.getRankings().stream().forEach(data -> {
			if (data.getArea().equals(TEST_AREA)) {
				areaStatusMap.putIfAbsent(RankingArea.TEST, testRankingStatus);
				RankingStatus status = new RankingStatus();
				setField(data.getField(), status);
				setRankings(data, status);
				testRankingStatus.add(status);
			}

			if (data.getArea().equals(ODI_AREA)) {
				areaStatusMap.putIfAbsent(RankingArea.ODI, odiRankingStatus);
				RankingStatus status = new RankingStatus();
				setField(data.getField(), status);
				setRankings(data, status);
				odiRankingStatus.add(status);
			}

			if (data.getArea().equals(T20_AREA)) {
				areaStatusMap.putIfAbsent(RankingArea.T20, t20RankingStatus);
				RankingStatus status = new RankingStatus();
				setField(data.getField(), status);
				setRankings(data, status);
				t20RankingStatus.add(status);
			}
		});

		areaStatusMap.entrySet().forEach(entry -> {
			RankingInfo rankingInfo = new RankingInfo();
			rankingInfo.setFormat(entry.getKey());
			rankingInfo.setStatus(entry.getValue());

			rankingInfoList.add(rankingInfo);
		});

		return ranking;
	}

	private void setRankings(RankingInfoData data, RankingStatus status) {
		status.setBestRanking(data.getBestRanking());
		status.setCurrentRanking(data.getCurrentRanking());
	}

	private void setField(String filed, RankingStatus status) {
		switch (filed) {
		case FIELD_BAT:
			status.setField(RankingField.BAT);
			break;
		case FIELD_BOWL:
			status.setField(RankingField.BOWL);
			break;
		case FILED_ALLROUND:
			status.setField(RankingField.ALLROUND);
			break;
		}
	}

	public Page<IccRanking> convertIccRankingDataPageToIccRankingPage(Page<IccRankingData> rankingDataPage,
			Pageable pageable) {
		List<IccRanking> rankingList = rankingDataPage.toList().stream()
				.map(rankingData -> mapIccDataModelToApiExposedModel(rankingData)).collect(Collectors.toList());

		Page<IccRanking> iccRankingPage = new PageImpl<IccRanking>(rankingList, pageable, rankingList.size());

		return iccRankingPage;
	}
}
