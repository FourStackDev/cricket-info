package org.fourstack.playcricket.battinginfo.converters;

import static org.fourstack.playcricket.battinginfo.codetype.CricketFormat.ODI;
import static org.fourstack.playcricket.battinginfo.codetype.CricketFormat.T20;
import static org.fourstack.playcricket.battinginfo.codetype.CricketFormat.TEST;
import static org.fourstack.playcricket.battinginfo.constants.BattingInfoServiceConstants.FORMAT_ODI;
import static org.fourstack.playcricket.battinginfo.constants.BattingInfoServiceConstants.FORMAT_T20;
import static org.fourstack.playcricket.battinginfo.constants.BattingInfoServiceConstants.FORMAT_TEST;

import org.fourstack.playcricket.battinginfo.models.BattingInfo;
import org.fourstack.playcricket.battinginfo.models.data.BattingStatistics;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class BattingStatisticsToBattingInfoConverter implements Converter<BattingStatistics, BattingInfo> {

	@Override
	public BattingInfo convert(BattingStatistics source) {
		BattingInfo info = new BattingInfo();
		info.setAverage(source.getAverage());
		info.setBalls(source.getBalls());
		info.setDucks(source.getDucks());
		setFormat(info, source.getFormat());
		info.setFours(source.getFours());
		info.setHighest(source.getHighest());
		info.setInnings(source.getInnings());
		info.setMatches(source.getMatches());
		info.setNoOfCenturies(source.getNoOfCenturies());
		info.setNoOfDoubleCenturies(source.getNoOfDoubleCenturies());
		info.setNoOfHalfCenturies(source.getNoOfHalfCenturies());
		info.setNoOfQuadrupleCenturies(source.getNoOfQuadrupleCenturies());
		info.setNoOfTripleCenturies(source.getNoOfTripleCenturies());
		info.setNumberOfNotOuts(source.getNumberOfNotOuts());
		info.setRuns(source.getRuns());
		info.setSixes(source.getSixes());
		info.setStrikeRate(source.getStrikeRate());

		return info;
	}
	
	private void setFormat(BattingInfo battingInfo, String format) {
		switch (format) {
		case FORMAT_TEST:
			battingInfo.setFormat(TEST);
			break;
		case FORMAT_ODI:
			battingInfo.setFormat(ODI);
			break;
		case FORMAT_T20:
			battingInfo.setFormat(T20);
			break;
		}
	}

}
