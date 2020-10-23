package org.fourstack.playcricket.bowlinginfo;

import java.util.ArrayList;
import java.util.List;

import org.fourstack.playcricket.bowlinginfo.codetype.CricketFormat;
import org.fourstack.playcricket.bowlinginfo.models.BowlingInfo;
import org.fourstack.playcricket.bowlinginfo.models.PlayerBowlingInfo;
import org.fourstack.playcricket.bowlinginfo.services.BowlingInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
public class InitializeData implements CommandLineRunner{
	
	@Autowired
	private BowlingInfoService service;

	@Override
	public void run(String... args) throws Exception {		
		for (int i = 0; i < 1000; i++) {
			PlayerBowlingInfo info = new PlayerBowlingInfo();
			info.setPlayerId("JAD000"+i);
			info.setBowlingStatistics(getBowlingStatistics());
			
			service.savePlayerBowlingStatistics(info);
		}
	}

	private List<BowlingInfo> getBowlingStatistics() {
		BowlingInfo testBowling = new BowlingInfo();
		testBowling.setFormat(CricketFormat.TEST);
		testBowling.setMatches(71);
		testBowling.setInnings(132);
		testBowling.setBalls(19586);
		testBowling.setRuns(9282);
		testBowling.setMaidens(672);
		testBowling.setWickets(365);
		testBowling.setAverage(25.43);
		testBowling.setEconomy(2.84);
		testBowling.setStrikeRate(53.66);
		testBowling.setBestBowlingInInnings("7/59");
		testBowling.setBestBowlingInMatch("13/140");
		testBowling.setFourWicketHaul(17);
		testBowling.setFiveWicketHaul(27);
		testBowling.setTenWicketHaul(7);
		
		BowlingInfo odiBowling = new BowlingInfo();
		odiBowling.setFormat(CricketFormat.ODI);
		odiBowling.setMatches(111);
		odiBowling.setInnings(109);
		odiBowling.setBalls(6021);
		odiBowling.setRuns(4937);
		odiBowling.setMaidens(35);
		odiBowling.setWickets(150);
		odiBowling.setAverage(32.91);
		odiBowling.setEconomy(4.92);
		odiBowling.setStrikeRate(40.14);
		odiBowling.setBestBowlingInInnings("4/25");
		odiBowling.setBestBowlingInMatch("4/25");
		odiBowling.setFourWicketHaul(1);
		odiBowling.setFiveWicketHaul(0);
		odiBowling.setTenWicketHaul(0);
		
		BowlingInfo t20Bowling = new BowlingInfo();
		t20Bowling.setFormat(CricketFormat.T20);
		t20Bowling.setMatches(46);
		t20Bowling.setInnings(46);
		t20Bowling.setBalls(1026);
		t20Bowling.setRuns(1193);
		t20Bowling.setMaidens(2);
		t20Bowling.setWickets(52);
		t20Bowling.setAverage(22.94);
		t20Bowling.setEconomy(6.98);
		t20Bowling.setStrikeRate(19.73);
		t20Bowling.setBestBowlingInInnings("4/8");
		t20Bowling.setBestBowlingInMatch("4/8");
		t20Bowling.setFourWicketHaul(2);
		t20Bowling.setFiveWicketHaul(0);
		t20Bowling.setTenWicketHaul(0);
		
		List<BowlingInfo> bowlingList = new ArrayList<>();
		bowlingList.add(testBowling);
		bowlingList.add(odiBowling);
		bowlingList.add(t20Bowling);
		return bowlingList;
	}

}
