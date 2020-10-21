package org.fourstack.playcricket.rankinginfo.repositories;

import java.util.Optional;

import org.fourstack.playcricket.rankinginfo.models.data.IccRankingData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IccRankingRepository extends JpaRepository<IccRankingData, String> {

	public Optional<IccRankingData> findByPlayerId(String playerId);

}
