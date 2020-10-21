package org.fourstack.playcricket.rankinginfo.repositories;

import org.fourstack.playcricket.rankinginfo.models.data.RankingInfoData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RankingInfoDataRepository extends JpaRepository<RankingInfoData, String>{

}
