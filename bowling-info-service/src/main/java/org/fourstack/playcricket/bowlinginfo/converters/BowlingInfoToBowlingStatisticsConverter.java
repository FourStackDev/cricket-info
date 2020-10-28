package org.fourstack.playcricket.bowlinginfo.converters;

import static org.fourstack.playcricket.bowlinginfo.constants.BowlingInfoConstants.FORMAT_ODI;
import static org.fourstack.playcricket.bowlinginfo.constants.BowlingInfoConstants.FORMAT_T20;
import static org.fourstack.playcricket.bowlinginfo.constants.BowlingInfoConstants.FORMAT_TEST;

import org.fourstack.playcricket.bowlinginfo.codetype.CricketFormat;
import org.fourstack.playcricket.bowlinginfo.models.BowlingInfo;
import org.fourstack.playcricket.bowlinginfo.models.data.BowlingStatistics;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BowlingInfoToBowlingStatisticsConverter implements Converter<BowlingInfo, BowlingStatistics>{

	@Override
	public BowlingStatistics convert(BowlingInfo source) {
		BowlingStatistics data = new BowlingStatistics();
		setFormat(data, source.getFormat());
		data.setMatches(source.getMatches());
		data.setInnings(source.getInnings());
		data.setBalls(source.getBalls());
		data.setRuns(source.getRuns());
		data.setMaidens(source.getMaidens());
		data.setWickets(source.getWickets());
		data.setAverage(source.getAverage());
		data.setEconomy(source.getEconomy());
		data.setStrikeRate(source.getStrikeRate());
		data.setBestBowlingInInnings(source.getBestBowlingInInnings());
		data.setBestBowlingInMatch(source.getBestBowlingInMatch());
		data.setFourWicketHaul(source.getFourWicketHaul());
		data.setFiveWicketHaul(source.getFiveWicketHaul());
		data.setTenWicketHaul(source.getTenWicketHaul());
		return data;
	}
	
	private void setFormat(BowlingStatistics data, CricketFormat format) {
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

}
