package org.fourstack.playcricket.bowlinginfo.converters;

import java.util.ArrayList;
import java.util.List;

import org.fourstack.playcricket.bowlinginfo.models.BowlingInfo;
import org.fourstack.playcricket.bowlinginfo.models.PlayerBowlingInfo;
import org.fourstack.playcricket.bowlinginfo.models.data.BowlingStatistics;
import org.fourstack.playcricket.bowlinginfo.models.data.PlayerBowlingData;
import org.fourstack.playcricket.bowlinginfo.util.IdGenerationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class PlayerBowlingInfoToPalyerBowlingDataConverter implements Converter<PlayerBowlingInfo, PlayerBowlingData> {

	/*
	 * @Autowired private ConversionService bowlingInfoToStatisticsConverter;
	 */
	
	@Autowired
	private BowlingInfoToBowlingStatisticsConverter infoToStatisticsConverter;

	@Override
	public PlayerBowlingData convert(PlayerBowlingInfo source) {
		System.out.println(
				"PlayerBowlingInfoToPalyerBowlingDataConverter:: CONVERTING PlayerBowlingInfo TO PlayerBowlingData");
		PlayerBowlingData data = new PlayerBowlingData();
		data.setPlayerBowlingInfoId(IdGenerationUtil.generatePlayerBowlingInfoId(source.getPlayerId()));
		data.setPlayerId(source.getPlayerId());

		data.setStatistics(generatePlayerStatistics(source.getBowlingStatistics(), data.getPlayerId()));
		return data;
	}

	public List<BowlingStatistics> generatePlayerStatistics(List<BowlingInfo> bowlingInfoList, String playerId) {
		List<BowlingStatistics> bowlingInfoDataList = new ArrayList<>();
		for (BowlingInfo info : bowlingInfoList) {
			BowlingStatistics data = mapBowlingInfoToData(info);
			data.setBowlingInfoId(IdGenerationUtil.generateBowlingInfoId(playerId, info.getFormat().name()));
			bowlingInfoDataList.add(data);
		}
		return bowlingInfoDataList;
	}

	public BowlingStatistics mapBowlingInfoToData(BowlingInfo info) {
		System.out.println(
				"---------------------PlayerBowlingInfoToPalyerBowlingDataConverter :: CONVERTING BOWLING INFO TO STATISTICS------------------------------");
		BowlingStatistics data = infoToStatisticsConverter.convert(info);
		return data;
	}

}
