package org.fourstack.playcricket.rankinginfo;

import java.util.Arrays;

import org.fourstack.playcricket.rankinginfo.codetype.RankingArea;
import org.fourstack.playcricket.rankinginfo.codetype.RankingField;
import org.fourstack.playcricket.rankinginfo.models.IccRanking;
import org.fourstack.playcricket.rankinginfo.models.RankingInfo;
import org.fourstack.playcricket.rankinginfo.models.RankingStatus;
import org.fourstack.playcricket.rankinginfo.service.RankingInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitializeData implements CommandLineRunner {

	@Autowired
	private RankingInfoService service;

	@Override
	public void run(String... args) throws Exception {

		for (int i = 0; i < 1000; i++) {
			IccRanking ranking1 = new IccRanking();
			ranking1.setPlayerId("VIR000" + i);

			RankingInfo testRanking = new RankingInfo();
			testRanking.setFormat(RankingArea.TEST);
			RankingStatus status1 = new RankingStatus();
			status1.setBestRanking(1);
			status1.setCurrentRanking(2);
			status1.setField(RankingField.BAT);
			RankingStatus status2 = new RankingStatus();
			status2.setBestRanking(3);
			status2.setCurrentRanking(4);
			status2.setField(RankingField.BOWL);
			testRanking.setStatus(Arrays.asList(status1, status2));

			RankingInfo odiRanking = new RankingInfo();
			odiRanking.setFormat(RankingArea.ODI);
			odiRanking.setStatus(Arrays.asList(status1, status2));

			RankingInfo t20Ranking = new RankingInfo();
			t20Ranking.setFormat(RankingArea.T20);
			RankingStatus status3 = new RankingStatus();
			status3.setBestRanking(9);
			status3.setCurrentRanking(12);
			status3.setField(RankingField.ALLROUND);
			t20Ranking.setStatus(Arrays.asList(status1, status2, status3));

			ranking1.setRankingInfo(Arrays.asList(testRanking, odiRanking, t20Ranking));
			service.saveIccRanking(ranking1);
		}
	}

}
