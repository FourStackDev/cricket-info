package org.fourstack.playcricket.battinginfo.repositories;

import org.fourstack.playcricket.battinginfo.models.data.BattingStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BattingStatisticsRepository extends JpaRepository<BattingStatistics, String>{

}
