package org.fourstack.playcricket.bowlinginfo.converters;

import static org.fourstack.playcricket.bowlinginfo.codetype.CricketFormat.ODI;
import static org.fourstack.playcricket.bowlinginfo.codetype.CricketFormat.T20;
import static org.fourstack.playcricket.bowlinginfo.codetype.CricketFormat.TEST;
import static org.fourstack.playcricket.bowlinginfo.constants.BowlingInfoConstants.FORMAT_ODI;
import static org.fourstack.playcricket.bowlinginfo.constants.BowlingInfoConstants.FORMAT_T20;
import static org.fourstack.playcricket.bowlinginfo.constants.BowlingInfoConstants.FORMAT_TEST;

import org.fourstack.playcricket.bowlinginfo.models.BowlingInfo;
import org.fourstack.playcricket.bowlinginfo.models.data.BowlingStatistics;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class BowlingStatisticsToBowlingInfoConverter implements Converter<BowlingStatistics, BowlingInfo> {

	@Override
	public BowlingInfo convert(BowlingStatistics source) {
		BowlingInfo info = new BowlingInfo();
		setFormat(info, source.getFormat());

		info.setMatches(source.getMatches());
		info.setInnings(source.getInnings());
		info.setBalls(source.getBalls());
		info.setRuns(source.getRuns());
		info.setMaidens(source.getMaidens());
		info.setWickets(source.getWickets());
		info.setAverage(source.getAverage());
		info.setEconomy(source.getEconomy());
		info.setStrikeRate(source.getStrikeRate());
		info.setBestBowlingInInnings(source.getBestBowlingInInnings());
		info.setBestBowlingInMatch(source.getBestBowlingInMatch());
		info.setFourWicketHaul(source.getFourWicketHaul());
		info.setFiveWicketHaul(source.getFiveWicketHaul());
		info.setTenWicketHaul(source.getTenWicketHaul());
		return info;
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
}
