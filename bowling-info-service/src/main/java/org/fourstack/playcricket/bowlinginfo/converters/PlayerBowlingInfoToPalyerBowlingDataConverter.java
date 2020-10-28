package org.fourstack.playcricket.bowlinginfo.converters;

import static org.fourstack.playcricket.bowlinginfo.constants.BowlingInfoConstants.BOWLING_INFO_ID_PREFIX;
import static org.fourstack.playcricket.bowlinginfo.constants.BowlingInfoConstants.HYPEN;

import java.util.ArrayList;
import java.util.List;

import org.fourstack.playcricket.bowlinginfo.models.BowlingInfo;
import org.fourstack.playcricket.bowlinginfo.models.PlayerBowlingInfo;
import org.fourstack.playcricket.bowlinginfo.models.data.BowlingInfoData;
import org.fourstack.playcricket.bowlinginfo.models.data.PlayerBowlingInfoData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PlayerBowlingInfoToPalyerBowlingDataConverter
		implements Converter<PlayerBowlingInfo, PlayerBowlingInfoData> {

	@Autowired
	private BowlingInfoToBowlingDataConverter bowlingDataConverter;

	@Override
	public PlayerBowlingInfoData convert(PlayerBowlingInfo source) {
		PlayerBowlingInfoData data = new PlayerBowlingInfoData();
		data.setPlayerBowlingInfoId(generatePlayerBowlingInfoId(source.getPlayerId()));
		data.setPlayerId(source.getPlayerId());

		data.setStatistics(generatePlayerStatistics(source.getBowlingStatistics(), data.getPlayerId()));
		return data;
	}

	public List<BowlingInfoData> generatePlayerStatistics(List<BowlingInfo> bowlingInfoList, String playerId) {
		List<BowlingInfoData> bowlingInfoDataList = new ArrayList<>();
		for (BowlingInfo info : bowlingInfoList) {
			BowlingInfoData data = mapBowlingInfoToData(playerId, info);
			bowlingInfoDataList.add(data);
		}
		return bowlingInfoDataList;
	}

	public BowlingInfoData mapBowlingInfoToData(String playerId, BowlingInfo info) {
		BowlingInfoData data = bowlingDataConverter.convert(info);
		data.setBowlingInfoId(generateBowlingInfoId(playerId, info.getFormat().name()));
		return data;
	}

	private String generateBowlingInfoId(String playerId, String format) {
		return BOWLING_INFO_ID_PREFIX + format + HYPEN + playerId;
	}

	private String generatePlayerBowlingInfoId(String playerId) {
		return BOWLING_INFO_ID_PREFIX + playerId;
	}

}
