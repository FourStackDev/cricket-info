package org.fourstack.playcricket.bowlinginfo.repositories;

import org.fourstack.playcricket.bowlinginfo.models.data.BowlingStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BowlingInfoDataRepository extends JpaRepository<BowlingStatistics, String>{

}
