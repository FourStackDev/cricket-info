package org.fourstack.playcricket.battinginfo.converters;

import java.util.ArrayList;
import java.util.List;

import org.fourstack.playcricket.battinginfo.models.BattingInfo;
import org.fourstack.playcricket.battinginfo.models.PlayerBattingInfo;
import org.fourstack.playcricket.battinginfo.models.data.BattingStatistics;
import org.fourstack.playcricket.battinginfo.models.data.PlayerBattingData;
import org.fourstack.playcricket.battinginfo.util.BattingServiceIdGenerationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class PlayerBattingInfoToDataConverter implements Converter<PlayerBattingInfo, PlayerBattingData> {

	@Autowired
	private BattingInfoToBattingStatisticsConverter battingInfoToStatisticsConverter;

	@Override
	public PlayerBattingData convert(PlayerBattingInfo source) {
		PlayerBattingData dataBaseModel = new PlayerBattingData();

		dataBaseModel.setPalyerBattingInfoId(
				BattingServiceIdGenerationUtil.generatePlayerBattingInfoId(source.getPlayerId()));
		dataBaseModel.setPlayerId(source.getPlayerId());

		dataBaseModel.setStatistics(generatePlayersStatistics(source.getBattingStatistics(), source.getPlayerId()));

		return dataBaseModel;
	}

	public List<BattingStatistics> generatePlayersStatistics(List<BattingInfo> battingInfoList, String playerId) {
		List<BattingStatistics> battingDataList = new ArrayList<>();

		for (BattingInfo battingInfo : battingInfoList) {
			BattingStatistics battingInfoData = battingInfoToStatisticsConverter.convert(battingInfo);
			battingInfoData.setBattingInfoId(
					BattingServiceIdGenerationUtil.generateBattingInfoId(playerId, battingInfoData.getFormat()));
			battingDataList.add(battingInfoData);
		}

		return battingDataList;
	}

}
