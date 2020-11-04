package org.fourstack.playcricket.battinginfo.repositories;

import java.util.Optional;

import org.fourstack.playcricket.battinginfo.models.data.PlayerBattingData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerBattingDataRepository extends JpaRepository<PlayerBattingData, String> {

	public Optional<PlayerBattingData> findByPlayerId(String playerId);
}
