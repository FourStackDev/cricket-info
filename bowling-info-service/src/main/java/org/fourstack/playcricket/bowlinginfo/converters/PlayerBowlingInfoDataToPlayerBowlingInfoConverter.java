package org.fourstack.playcricket.bowlinginfo.converters;

import java.util.ArrayList;
import java.util.List;

import org.fourstack.playcricket.bowlinginfo.models.BowlingInfo;
import org.fourstack.playcricket.bowlinginfo.models.PlayerBowlingInfo;
import org.fourstack.playcricket.bowlinginfo.models.data.BowlingInfoData;
import org.fourstack.playcricket.bowlinginfo.models.data.PlayerBowlingInfoData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class PlayerBowlingInfoDataToPlayerBowlingInfoConverter
		implements Converter<PlayerBowlingInfoData, PlayerBowlingInfo> {
	
	@Autowired
	private ConversionService bowlingInfoConverter;

	@Override
	public PlayerBowlingInfo convert(PlayerBowlingInfoData source) {
		PlayerBowlingInfo info = new PlayerBowlingInfo();
		info.setBowlingInfoId(source.getPlayerBowlingInfoId());
		info.setPlayerId(source.getPlayerId());
		info.setBowlingStatistics(generatePlayerStatistics(source.getStatistics()));

		return info;
	}

	public List<BowlingInfo> generatePlayerStatistics(List<BowlingInfoData> bowlingDataList) {
		List<BowlingInfo> bowlingInfoList = new ArrayList<>();

		for (BowlingInfoData data : bowlingDataList) {
			BowlingInfo info = mapBowlingDataToInfoModel(data);
			bowlingInfoList.add(info);
		}
		return bowlingInfoList;
	}

	public BowlingInfo mapBowlingDataToInfoModel(BowlingInfoData data) {
		return bowlingInfoConverter.convert(data, BowlingInfo.class);
	}
}
