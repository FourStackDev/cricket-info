package org.fourstack.playcricket.battinginfo.converters;

import java.util.ArrayList;
import java.util.List;

import org.fourstack.playcricket.battinginfo.models.BattingInfo;
import org.fourstack.playcricket.battinginfo.models.PlayerBattingInfo;
import org.fourstack.playcricket.battinginfo.models.data.BattingStatistics;
import org.fourstack.playcricket.battinginfo.models.data.PlayerBattingData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class PlayerBattingDataToInfoConverter implements Converter<PlayerBattingData, PlayerBattingInfo> {
	
	@Autowired
	private BattingStatisticsToBattingInfoConverter battingStatisticsToInfoConverter;

	@Override
	public PlayerBattingInfo convert(PlayerBattingData source) {
		PlayerBattingInfo battingInfo = new PlayerBattingInfo();
		battingInfo.setBattingInfoId(source.getPalyerBattingInfoId());
		battingInfo.setPlayerId(source.getPlayerId());

		battingInfo.setBattingStatistics(generatePlayersStatistics(source.getStatistics()));
		return battingInfo;
	}
	
	public List<BattingInfo> generatePlayersStatistics(List<BattingStatistics> battingDataList) {
		List<BattingInfo> battingList = new ArrayList<>();

		for (BattingStatistics data : battingDataList) {
			BattingInfo info = battingStatisticsToInfoConverter.convert(data);
			battingList.add(info);
		}
		return battingList;
	}

}
