package org.fourstack.playcricket.battinginfo.repositories;

import org.fourstack.playcricket.battinginfo.models.data.BattingInfoData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BattingInfoDataRepository extends JpaRepository<BattingInfoData, String>{

}
