package org.fourstack.playcricket.rankinginfo.service;

import java.util.List;

import org.fourstack.playcricket.rankinginfo.models.IccRanking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RankingInfoService {
	
	public List<IccRanking> getIccRankingList();
	
	public Page<IccRanking> getIccRankingList(Pageable pageable);
	
	public IccRanking getIccRankingById(String rankingId);
	
	public IccRanking getIccRankingByPlayerId(String playerId);
	
	public IccRanking saveIccRanking(IccRanking ranking);
}