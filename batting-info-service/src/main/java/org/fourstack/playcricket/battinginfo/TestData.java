package org.fourstack.playcricket.battinginfo;

import java.util.ArrayList;
import java.util.List;

import org.fourstack.playcricket.battinginfo.codetype.CricketFormat;
import org.fourstack.playcricket.battinginfo.helpers.BattingDataModelMappingHelper;
import org.fourstack.playcricket.battinginfo.models.BattingInfo;
import org.fourstack.playcricket.battinginfo.models.PlayerBattingInfo;
import org.fourstack.playcricket.battinginfo.models.data.PlayerBattingInfoData;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestData {

	public static void main(String[] args) {

		PlayerBattingInfo battingInfo = new PlayerBattingInfo();
		battingInfo.setPlayerId("SAC0001");
		battingInfo.setBattingStatistics(generatePlayerStatistics());

	}

	private static List<BattingInfo> generatePlayerStatistics() {
		BattingInfo testBattingInfo = new BattingInfo();
		testBattingInfo.setFormat(CricketFormat.TEST);
		testBattingInfo.setAverage(53.79);
		testBattingInfo.setBalls(29437);
		testBattingInfo.setMatches(200);
		testBattingInfo.setInnings(329);
		testBattingInfo.setRuns(15921);
		testBattingInfo.setHighest(248);
		testBattingInfo.setStrikeRate(54.08);
		testBattingInfo.setNumberOfNotOuts(33);
		testBattingInfo.setFours(2058);
		testBattingInfo.setSixes(69);
		testBattingInfo.setDucks(14);
		testBattingInfo.setNoOfHalfCenturies(68);
		testBattingInfo.setNoOfCenturies(51);
		testBattingInfo.setNoOfDoubleCenturies(6);
		testBattingInfo.setNoOfTripleCenturies(0);
		testBattingInfo.setNoOfQuadrupleCenturies(0);

		BattingInfo odiBattingInfo = new BattingInfo();
		odiBattingInfo.setFormat(CricketFormat.ODI);
		odiBattingInfo.setAverage(44.83);
		odiBattingInfo.setBalls(21367);
		odiBattingInfo.setMatches(463);
		odiBattingInfo.setInnings(452);
		odiBattingInfo.setRuns(18426);
		odiBattingInfo.setHighest(200);
		odiBattingInfo.setStrikeRate(86.24);
		odiBattingInfo.setNumberOfNotOuts(41);
		odiBattingInfo.setFours(2016);
		odiBattingInfo.setSixes(195);
		odiBattingInfo.setDucks(20);
		odiBattingInfo.setNoOfHalfCenturies(96);
		odiBattingInfo.setNoOfCenturies(49);
		odiBattingInfo.setNoOfDoubleCenturies(1);
		odiBattingInfo.setNoOfTripleCenturies(0);
		odiBattingInfo.setNoOfQuadrupleCenturies(0);

		BattingInfo t20BattingInfo = new BattingInfo();
		t20BattingInfo.setFormat(CricketFormat.T20);
		t20BattingInfo.setAverage(10.0);
		t20BattingInfo.setBalls(12);
		t20BattingInfo.setMatches(1);
		t20BattingInfo.setInnings(1);
		t20BattingInfo.setRuns(10);
		t20BattingInfo.setHighest(10);
		t20BattingInfo.setStrikeRate(83.33);
		t20BattingInfo.setNumberOfNotOuts(0);
		t20BattingInfo.setFours(2);
		t20BattingInfo.setSixes(0);
		t20BattingInfo.setDucks(0);
		t20BattingInfo.setNoOfHalfCenturies(0);
		t20BattingInfo.setNoOfCenturies(0);
		t20BattingInfo.setNoOfDoubleCenturies(0);
		t20BattingInfo.setNoOfTripleCenturies(0);
		t20BattingInfo.setNoOfQuadrupleCenturies(0);

		List<BattingInfo> battingInfoList = new ArrayList<>();
		battingInfoList.add(testBattingInfo);
		battingInfoList.add(odiBattingInfo);
		battingInfoList.add(t20BattingInfo);

		return battingInfoList;
	}
}
