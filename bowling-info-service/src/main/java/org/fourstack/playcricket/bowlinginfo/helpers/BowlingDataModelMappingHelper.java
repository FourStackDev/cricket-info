package org.fourstack.playcricket.bowlinginfo.helpers;

import static org.fourstack.playcricket.bowlinginfo.constants.BowlingInfoConstants.*;
import static org.fourstack.playcricket.bowlinginfo.codetype.CricketFormat.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.fourstack.playcricket.bowlinginfo.codetype.CricketFormat;
import org.fourstack.playcricket.bowlinginfo.models.BowlingInfo;
import org.fourstack.playcricket.bowlinginfo.models.PlayerBowlingInfo;
import org.fourstack.playcricket.bowlinginfo.models.data.BowlingInfoData;
import org.fourstack.playcricket.bowlinginfo.models.data.PlayerBowlingInfoData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class BowlingDataModelMappingHelper {

	public PlayerBowlingInfoData mapBowlingInfoToDatabaseModel(PlayerBowlingInfo bowlingInfo) {
		PlayerBowlingInfoData data = new PlayerBowlingInfoData();
		data.setPlayerBowlingInfoId(generatePlayerBowlingInfoId(bowlingInfo.getPlayerId()));
		data.setPlayerId(bowlingInfo.getPlayerId());

		data.setStatistics(generatePlayerStatistics(bowlingInfo.getBowlingStatistics(), data.getPlayerId()));
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
		BowlingInfoData data = new BowlingInfoData();
		setFormat(data, info.getFormat());
		data.setBowlingInfoId(generateBowlingInfoId(playerId, info.getFormat().name()));
		data.setMatches(info.getMatches());
		data.setInnings(info.getInnings());
		data.setBalls(info.getBalls());
		data.setRuns(info.getRuns());
		data.setMaidens(info.getMaidens());
		data.setWickets(info.getWickets());
		data.setAverage(info.getAverage());
		data.setEconomy(info.getEconomy());
		data.setStrikeRate(info.getStrikeRate());
		data.setBestBowlingInInnings(info.getBestBowlingInInnings());
		data.setBestBowlingInMatch(info.getBestBowlingInMatch());
		data.setFourWicketHaul(info.getFourWicketHaul());
		data.setFiveWicketHaul(info.getFiveWicketHaul());
		data.setTenWicketHaul(info.getTenWicketHaul());
		return data;
	}

	private void setFormat(BowlingInfoData data, CricketFormat format) {
		switch (format) {
		case TEST:
			data.setFormat(FORMAT_TEST);
			break;
		case ODI:
			data.setFormat(FORMAT_ODI);
			break;
		case T20:
			data.setFormat(FORMAT_T20);
			break;
		}
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

	public PlayerBowlingInfo mapBowlingDatabaseModelToApiExposedModel(PlayerBowlingInfoData data) {
		PlayerBowlingInfo info = new PlayerBowlingInfo();
		info.setBowlingInfoId(data.getPlayerBowlingInfoId());
		info.setPlayerId(data.getPlayerId());
		info.setBowlingStatistics(generatePlayerStatistics(data.getStatistics()));

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

	private String generatePlayerBowlingInfoId(String playerId) {
		return BOWLING_INFO_ID_PREFIX + playerId;
	}

	private String generateBowlingInfoId(String playerId, String format) {
		return BOWLING_INFO_ID_PREFIX + format + HYPEN + playerId;
	}

	public Page<PlayerBowlingInfo> convertBowlingDataPageToBowlingInfoPage(Page<PlayerBowlingInfoData> bowlingDataPage,
			Pageable pageable) {
		List<PlayerBowlingInfo> infoList = bowlingDataPage.toList().stream()
				.map(data -> mapBowlingDatabaseModelToApiExposedModel(data)).collect(Collectors.toList());

		return new PageImpl<PlayerBowlingInfo>(infoList, pageable, infoList.size());
	}

}
