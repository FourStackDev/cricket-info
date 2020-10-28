package org.fourstack.playcricket.bowlinginfo.helpers;

import static org.fourstack.playcricket.bowlinginfo.codetype.CricketFormat.ODI;
import static org.fourstack.playcricket.bowlinginfo.codetype.CricketFormat.T20;
import static org.fourstack.playcricket.bowlinginfo.codetype.CricketFormat.TEST;
import static org.fourstack.playcricket.bowlinginfo.constants.BowlingInfoConstants.FORMAT_ODI;
import static org.fourstack.playcricket.bowlinginfo.constants.BowlingInfoConstants.FORMAT_T20;
import static org.fourstack.playcricket.bowlinginfo.constants.BowlingInfoConstants.FORMAT_TEST;

import java.util.List;
import java.util.stream.Collectors;

import org.fourstack.playcricket.bowlinginfo.converters.PlayerBowlingInfoToPalyerBowlingDataConverter;
import org.fourstack.playcricket.bowlinginfo.models.BowlingInfo;
import org.fourstack.playcricket.bowlinginfo.models.PlayerBowlingInfo;
import org.fourstack.playcricket.bowlinginfo.models.data.BowlingStatistics;
import org.fourstack.playcricket.bowlinginfo.models.data.PlayerBowlingData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class BowlingDataModelMappingHelper {
	
	@Autowired
	private PlayerBowlingInfoToPalyerBowlingDataConverter bowlingDataConverter;
	
	@Autowired
	private ConversionService playerBowlingInfoConverter;

	public PlayerBowlingData mapBowlingInfoToDatabaseModel(PlayerBowlingInfo bowlingInfo) {
		return bowlingDataConverter.convert(bowlingInfo);
	}
	
	public BowlingStatistics mapBowlingInfoToData(String playerId, BowlingInfo bowlingInfo) {
		return bowlingDataConverter.mapBowlingInfoToData(playerId, bowlingInfo);
	}
	
	private void setFormat(BowlingInfo info, String format) {
		switch (format) {
		case FORMAT_TEST:
			info.setFormat(TEST);
			break;
		case FORMAT_ODI:
			info.setFormat(ODI);
			break;
		case FORMAT_T20:
			info.setFormat(T20);
			break;
		}
	}

	public PlayerBowlingInfo mapBowlingDatabaseModelToApiExposedModel(PlayerBowlingData data) {
		 return playerBowlingInfoConverter.convert(data, PlayerBowlingInfo.class);
	}

	public BowlingInfo mapBowlingDataToInfoModel(BowlingStatistics data) {
		BowlingInfo info = new BowlingInfo();
		setFormat(info, data.getFormat());

		info.setMatches(data.getMatches());
		info.setInnings(data.getInnings());
		info.setBalls(data.getBalls());
		info.setRuns(data.getRuns());
		info.setMaidens(data.getMaidens());
		info.setWickets(data.getWickets());
		info.setAverage(data.getAverage());
		info.setEconomy(data.getEconomy());
		info.setStrikeRate(data.getStrikeRate());
		info.setBestBowlingInInnings(data.getBestBowlingInInnings());
		info.setBestBowlingInMatch(data.getBestBowlingInMatch());
		info.setFourWicketHaul(data.getFourWicketHaul());
		info.setFiveWicketHaul(data.getFiveWicketHaul());
		info.setTenWicketHaul(data.getTenWicketHaul());
		return info;
	}
	
	public Page<PlayerBowlingInfo> convertBowlingDataPageToBowlingInfoPage(Page<PlayerBowlingData> bowlingDataPage,
			Pageable pageable) {
		List<PlayerBowlingInfo> infoList = bowlingDataPage.toList().stream()
				.map(data -> mapBowlingDatabaseModelToApiExposedModel(data)).collect(Collectors.toList());

		return new PageImpl<PlayerBowlingInfo>(infoList, pageable, infoList.size());
	}

}
