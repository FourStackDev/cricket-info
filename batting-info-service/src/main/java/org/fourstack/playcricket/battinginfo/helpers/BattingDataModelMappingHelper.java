package org.fourstack.playcricket.battinginfo.helpers;

import static org.fourstack.playcricket.battinginfo.codetype.CricketFormat.ODI;
import static org.fourstack.playcricket.battinginfo.codetype.CricketFormat.T20;
import static org.fourstack.playcricket.battinginfo.codetype.CricketFormat.TEST;
import static org.fourstack.playcricket.battinginfo.constants.BattingInfoServiceConstants.BATTING_INFO_ID_PREFIX;
import static org.fourstack.playcricket.battinginfo.constants.BattingInfoServiceConstants.FORMAT_ODI;
import static org.fourstack.playcricket.battinginfo.constants.BattingInfoServiceConstants.FORMAT_T20;
import static org.fourstack.playcricket.battinginfo.constants.BattingInfoServiceConstants.FORMAT_TEST;
import static org.fourstack.playcricket.battinginfo.constants.BattingInfoServiceConstants.HYPEN;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.fourstack.playcricket.battinginfo.codetype.CricketFormat;
import org.fourstack.playcricket.battinginfo.models.BattingInfo;
import org.fourstack.playcricket.battinginfo.models.PlayerBattingInfo;
import org.fourstack.playcricket.battinginfo.models.data.BattingInfoData;
import org.fourstack.playcricket.battinginfo.models.data.PlayerBattingInfoData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class BattingDataModelMappingHelper {

	public PlayerBattingInfoData mapBattingModelToDataBaseModel(PlayerBattingInfo battingInfo) {
		PlayerBattingInfoData dataBaseModel = new PlayerBattingInfoData();

		dataBaseModel.setPalyerBattingInfoId(generatePlayerBattingInfoId(battingInfo.getPlayerId()));
		dataBaseModel.setPlayerId(battingInfo.getPlayerId());

		dataBaseModel.setStatistics(
				generatePlayersStatistics(battingInfo.getBattingStatistics(), battingInfo.getPlayerId()));

		return dataBaseModel;
	}

	private List<BattingInfoData> generatePlayersStatistics(List<BattingInfo> battingInfoList, String playerId) {
		List<BattingInfoData> battingDataList = new ArrayList<>();

		for (BattingInfo battingInfo : battingInfoList) {
			BattingInfoData battingInfoData = new BattingInfoData();
			setFormat(battingInfoData, battingInfo.getFormat());
			battingInfoData.setMatches(battingInfo.getMatches());
			battingInfoData.setAverage(battingInfo.getAverage());
			battingInfoData.setBalls(battingInfo.getBalls());
			battingInfoData.setBattingInfoId(generateBattingInfoId(playerId, battingInfoData.getFormat()));
			battingInfoData.setDucks(battingInfo.getDucks());
			battingInfoData.setFours(battingInfo.getFours());
			battingInfoData.setHighest(battingInfo.getHighest());
			battingInfoData.setInnings(battingInfo.getInnings());
			battingInfoData.setNoOfCenturies(battingInfo.getNoOfCenturies());
			battingInfoData.setNoOfDoubleCenturies(battingInfo.getNoOfDoubleCenturies());
			battingInfoData.setNoOfHalfCenturies(battingInfo.getNoOfHalfCenturies());
			battingInfoData.setNoOfTripleCenturies(battingInfo.getNoOfTripleCenturies());
			battingInfoData.setNoOfQuadrupleCenturies(battingInfo.getNoOfQuadrupleCenturies());
			battingInfoData.setNumberOfNotOuts(battingInfo.getNumberOfNotOuts());
			battingInfoData.setRuns(battingInfo.getRuns());
			battingInfoData.setSixes(battingInfo.getSixes());
			battingInfoData.setStrikeRate(battingInfo.getStrikeRate());

			battingDataList.add(battingInfoData);
		}

		return battingDataList;

	}

	private void setFormat(BattingInfoData battingInfoData, CricketFormat format) {
		switch (format) {
		case TEST:
			battingInfoData.setFormat(FORMAT_TEST);
			break;
		case ODI:
			battingInfoData.setFormat(FORMAT_ODI);
			break;
		case T20:
			battingInfoData.setFormat(FORMAT_T20);
			break;
		}
	}

	private void setFormat(BattingInfo battingInfo, String format) {
		switch (format) {
		case FORMAT_TEST:
			battingInfo.setFormat(TEST);
			break;
		case FORMAT_ODI:
			battingInfo.setFormat(ODI);
			break;
		case FORMAT_T20:
			battingInfo.setFormat(T20);
			break;
		}
	}

	private String generatePlayerBattingInfoId(String playerId) {
		return BATTING_INFO_ID_PREFIX + playerId;
	}

	private String generateBattingInfoId(String playerId, String format) {
		return BATTING_INFO_ID_PREFIX + format + HYPEN + playerId;
	}

	public PlayerBattingInfo mapPlayerBattingDataModelToApiExposedModel(PlayerBattingInfoData battingInfoData) {
		PlayerBattingInfo battingInfo = new PlayerBattingInfo();
		battingInfo.setBattingInfoId(battingInfoData.getPalyerBattingInfoId());
		battingInfo.setPlayerId(battingInfoData.getPlayerId());

		battingInfo.setBattingStatistics(generatePlayersStatistics(battingInfoData.getStatistics()));
		return battingInfo;
	}

	private List<BattingInfo> generatePlayersStatistics(List<BattingInfoData> battingDataList) {
		List<BattingInfo> battingList = new ArrayList<>();

		for (BattingInfoData data : battingDataList) {
			BattingInfo info = new BattingInfo();
			info.setAverage(data.getAverage());
			info.setBalls(data.getBalls());
			info.setDucks(data.getDucks());
			setFormat(info, data.getFormat());
			info.setFours(data.getFours());
			info.setHighest(data.getHighest());
			info.setInnings(data.getInnings());
			info.setMatches(data.getMatches());
			info.setNoOfCenturies(data.getNoOfCenturies());
			info.setNoOfDoubleCenturies(data.getNoOfDoubleCenturies());
			info.setNoOfHalfCenturies(data.getNoOfHalfCenturies());
			info.setNoOfQuadrupleCenturies(data.getNoOfQuadrupleCenturies());
			info.setNoOfTripleCenturies(data.getNoOfTripleCenturies());
			info.setNumberOfNotOuts(data.getNumberOfNotOuts());
			info.setRuns(data.getRuns());
			info.setSixes(data.getSixes());
			info.setStrikeRate(data.getStrikeRate());

			battingList.add(info);
		}

		return battingList;
	}

	public Page<PlayerBattingInfo> convertPlayerBattingDataPageToPlayerBattingInfoPage(Page<PlayerBattingInfoData> playerDataPage,
			Pageable pageable) {
		List<PlayerBattingInfo> playerBattingInfoList = playerDataPage.toList().stream()
				.map(data -> mapPlayerBattingDataModelToApiExposedModel(data)).collect(Collectors.toList());

		Page<PlayerBattingInfo> battingInfoPage = new PageImpl<PlayerBattingInfo>(playerBattingInfoList, pageable,
				playerBattingInfoList.size());

		return battingInfoPage;
	}
}
