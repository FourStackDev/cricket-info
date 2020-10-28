package org.fourstack.playcricket.bowlinginfo.repositories;

import java.util.Optional;

import org.fourstack.playcricket.bowlinginfo.models.data.PlayerBowlingData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerBowlingInfoDataRepository extends JpaRepository<PlayerBowlingData, String> {

	public Optional<PlayerBowlingData> findByPlayerId(String playerId);

}
