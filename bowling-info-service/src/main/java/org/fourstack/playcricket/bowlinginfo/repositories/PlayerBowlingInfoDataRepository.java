package org.fourstack.playcricket.bowlinginfo.repositories;

import java.util.Optional;

import org.fourstack.playcricket.bowlinginfo.models.data.PlayerBowlingInfoData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerBowlingInfoDataRepository extends JpaRepository<PlayerBowlingInfoData, String> {

	public Optional<PlayerBowlingInfoData> findByPlayerId(String playerId);

}
