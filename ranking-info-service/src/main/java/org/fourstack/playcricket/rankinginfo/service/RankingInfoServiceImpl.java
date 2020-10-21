package org.fourstack.playcricket.rankinginfo.service;

import java.util.List;

import org.fourstack.playcricket.rankinginfo.helpers.RankingInfoServiceHelper;
import org.fourstack.playcricket.rankinginfo.models.IccRanking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RankingInfoServiceImpl implements RankingInfoService {

	@Autowired
	private RankingInfoServiceHelper rankingHelper;

	@Override
	public List<IccRanking> getIccRankingList() {
		return rankingHelper.getIccRankingList();
	}
	
	@Override
	public Page<IccRanking> getIccRankingList(Pageable pageable) {
		return rankingHelper.getIccRankingsPage(pageable);
	}

	@Override
	public IccRanking getIccRankingById(String rankingId) {
		return rankingHelper.getIccRankingById(rankingId);
	}

	@Override
	public IccRanking getIccRankingByPlayerId(String playerId) {
		return rankingHelper.getIccRankingByPlayerId(playerId);
	}

	@Override
	public IccRanking saveIccRanking(IccRanking ranking) {
		return rankingHelper.saveIccRanking(ranking);
	}
}
