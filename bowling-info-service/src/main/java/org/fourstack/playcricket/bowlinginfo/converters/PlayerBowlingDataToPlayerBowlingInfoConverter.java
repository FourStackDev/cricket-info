package org.fourstack.playcricket.bowlinginfo.converters;

import java.util.ArrayList;
import java.util.List;

import org.fourstack.playcricket.bowlinginfo.models.BowlingInfo;
import org.fourstack.playcricket.bowlinginfo.models.PlayerBowlingInfo;
import org.fourstack.playcricket.bowlinginfo.models.data.BowlingStatistics;
import org.fourstack.playcricket.bowlinginfo.models.data.PlayerBowlingData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class PlayerBowlingDataToPlayerBowlingInfoConverter
		implements Converter<PlayerBowlingData, PlayerBowlingInfo> {
	
	@Autowired	
	private BowlingStatisticsToBowlingInfoConverter bowlingStatisticsToInfoConverter;

	@Override
	public PlayerBowlingInfo convert(PlayerBowlingData source) {
		System.out.println(
				"PlayerBowlingDataToPlayerBowlingInfoConverter:: CONVERTING PlayerBowlingData TO PlayerBowlingInfo");
		PlayerBowlingInfo info = new PlayerBowlingInfo();
		info.setBowlingInfoId(source.getPlayerBowlingInfoId());
		info.setPlayerId(source.getPlayerId());
		info.setBowlingStatistics(generatePlayerStatistics(source.getStatistics()));

		return info;
	}

	public List<BowlingInfo> generatePlayerStatistics(List<BowlingStatistics> bowlingDataList) {
		List<BowlingInfo> bowlingInfoList = new ArrayList<>();

		for (BowlingStatistics data : bowlingDataList) {
			BowlingInfo info = mapBowlingDataToInfoModel(data);
			bowlingInfoList.add(info);
		}
		return bowlingInfoList;
	}

	public BowlingInfo mapBowlingDataToInfoModel(BowlingStatistics data) {
		return bowlingStatisticsToInfoConverter.convert(data);
	}
}
