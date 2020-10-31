package org.fourstack.playcricket.bowlinginfo.helpers;

import java.util.List;
import java.util.stream.Collectors;

import org.fourstack.playcricket.bowlinginfo.converters.BowlingInfoToBowlingStatisticsConverter;
import org.fourstack.playcricket.bowlinginfo.models.BowlingInfo;
import org.fourstack.playcricket.bowlinginfo.models.PlayerBowlingInfo;
import org.fourstack.playcricket.bowlinginfo.models.data.BowlingStatistics;
import org.fourstack.playcricket.bowlinginfo.models.data.PlayerBowlingData;
import org.fourstack.playcricket.bowlinginfo.util.IdGenerationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class BowlingDataModelMappingHelper {
	
	@Autowired
	private ConversionService playerBowlingDataToInfoConverter;

	@Autowired
	private ConversionService playerBowlingInfoToDataConverter;

	@Autowired
	private BowlingInfoToBowlingStatisticsConverter bowlingInfoToStatisticsConverter;

	public PlayerBowlingData mapBowlingInfoToDatabaseModel(PlayerBowlingInfo bowlingInfo) {
		playerBowlingInfoToDataConverter.canConvert(PlayerBowlingInfo.class, PlayerBowlingData.class);
		return playerBowlingInfoToDataConverter.convert(bowlingInfo, PlayerBowlingData.class);
	}

	public BowlingStatistics mapBowlingInfoToData(String playerId, BowlingInfo bowlingInfo) {
		BowlingStatistics bowlingStatistics = bowlingInfoToStatisticsConverter.convert(bowlingInfo);
		bowlingStatistics
				.setBowlingInfoId(IdGenerationUtil.generateBowlingInfoId(playerId, bowlingInfo.getFormat().name()));
		return bowlingStatistics;
	}

	public PlayerBowlingInfo mapBowlingDatabaseModelToApiExposedModel(PlayerBowlingData data) {
		return playerBowlingDataToInfoConverter.convert(data, PlayerBowlingInfo.class);
	}

	public Page<PlayerBowlingInfo> convertBowlingDataPageToBowlingInfoPage(Page<PlayerBowlingData> bowlingDataPage,
			Pageable pageable) {
		List<PlayerBowlingInfo> infoList = bowlingDataPage.toList().stream()
				.map(data -> mapBowlingDatabaseModelToApiExposedModel(data)).collect(Collectors.toList());

		return new PageImpl<PlayerBowlingInfo>(infoList, pageable, infoList.size());
	}
}
