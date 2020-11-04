package org.fourstack.playcricket.battinginfo.helpers;

import java.util.List;
import java.util.stream.Collectors;

import org.fourstack.playcricket.battinginfo.converters.BattingInfoToBattingStatisticsConverter;
import org.fourstack.playcricket.battinginfo.models.BattingInfo;
import org.fourstack.playcricket.battinginfo.models.PlayerBattingInfo;
import org.fourstack.playcricket.battinginfo.models.data.BattingStatistics;
import org.fourstack.playcricket.battinginfo.models.data.PlayerBattingData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class BattingDataModelMappingHelper {

	@Autowired
	private ConversionService playerBattingInfoToDataConverter;

	@Autowired
	private ConversionService playerBattingDataToInfoConverter;

	@Autowired
	private BattingInfoToBattingStatisticsConverter battingInfoToStatisticsConverter;

	public PlayerBattingData mapBattingModelToDataBaseModel(PlayerBattingInfo battingInfo) {
		PlayerBattingData dataBaseModel = playerBattingInfoToDataConverter.convert(battingInfo,
				PlayerBattingData.class);
		return dataBaseModel;
	}

	public PlayerBattingInfo mapPlayerBattingDataModelToApiExposedModel(PlayerBattingData battingInfoData) {
		PlayerBattingInfo battingInfo = playerBattingDataToInfoConverter.convert(battingInfoData,
				PlayerBattingInfo.class);
		return battingInfo;
	}

	public Page<PlayerBattingInfo> convertPlayerBattingDataPageToPlayerBattingInfoPage(
			Page<PlayerBattingData> playerDataPage, Pageable pageable) {
		List<PlayerBattingInfo> playerBattingInfoList = playerDataPage.toList()
																	  .stream()
																	  .map(data -> mapPlayerBattingDataModelToApiExposedModel(data))
																	  .collect(Collectors.toList());

		Page<PlayerBattingInfo> battingInfoPage = new PageImpl<PlayerBattingInfo>(playerBattingInfoList, pageable,
				playerBattingInfoList.size());

		return battingInfoPage;
	}

	public BattingStatistics convertBattingInfoToData(BattingInfo battingInfo) {
		return battingInfoToStatisticsConverter.convert(battingInfo);
	}
}
