package org.fourstack.playcricket.battinginfo.repositories;

import java.util.Optional;

import org.fourstack.playcricket.battinginfo.models.data.PlayerBattingInfoData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerBattingInfoRepository extends JpaRepository<PlayerBattingInfoData, String> {

	public Optional<PlayerBattingInfoData> findByPlayerId(String playerId);
}
