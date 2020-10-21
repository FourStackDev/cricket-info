package org.fourstack.playcricket.playerinfo.repositories;

import org.fourstack.playcricket.playerinfo.models.PlayerBasicInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerBasicInfoRepository extends JpaRepository<PlayerBasicInfo, Long>{

}
