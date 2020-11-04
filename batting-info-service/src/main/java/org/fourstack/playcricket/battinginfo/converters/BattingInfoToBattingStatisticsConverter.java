package org.fourstack.playcricket.battinginfo.converters;

import static org.fourstack.playcricket.battinginfo.constants.BattingInfoServiceConstants.FORMAT_ODI;
import static org.fourstack.playcricket.battinginfo.constants.BattingInfoServiceConstants.FORMAT_T20;
import static org.fourstack.playcricket.battinginfo.constants.BattingInfoServiceConstants.FORMAT_TEST;

import org.fourstack.playcricket.battinginfo.codetype.CricketFormat;
import org.fourstack.playcricket.battinginfo.models.BattingInfo;
import org.fourstack.playcricket.battinginfo.models.data.BattingStatistics;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class BattingInfoToBattingStatisticsConverter implements Converter<BattingInfo, BattingStatistics> {

	@Override
	public BattingStatistics convert(BattingInfo source) {
		BattingStatistics battingInfoData = new BattingStatistics();
		setFormat(battingInfoData, source.getFormat());
		battingInfoData.setMatches(source.getMatches());
		battingInfoData.setAverage(source.getAverage());
		battingInfoData.setBalls(source.getBalls());
		battingInfoData.setDucks(source.getDucks());
		battingInfoData.setFours(source.getFours());
		battingInfoData.setHighest(source.getHighest());
		battingInfoData.setInnings(source.getInnings());
		battingInfoData.setNoOfCenturies(source.getNoOfCenturies());
		battingInfoData.setNoOfDoubleCenturies(source.getNoOfDoubleCenturies());
		battingInfoData.setNoOfHalfCenturies(source.getNoOfHalfCenturies());
		battingInfoData.setNoOfTripleCenturies(source.getNoOfTripleCenturies());
		battingInfoData.setNoOfQuadrupleCenturies(source.getNoOfQuadrupleCenturies());
		battingInfoData.setNumberOfNotOuts(source.getNumberOfNotOuts());
		battingInfoData.setRuns(source.getRuns());
		battingInfoData.setSixes(source.getSixes());
		battingInfoData.setStrikeRate(source.getStrikeRate());

		return battingInfoData;
	}
	
	private void setFormat(BattingStatistics battingInfoData, CricketFormat format) {
		switch (format) {
		case TEST:
			battingInfoData.setFormat(FORMAT_TEST);
			break;
		case ODI:
			battingInfoData.setFormat(FORMAT_ODI);
			break;
		case T20:
			battingInfoData.setFormat(FORMAT_T20);
			break;
		}
	}

}
