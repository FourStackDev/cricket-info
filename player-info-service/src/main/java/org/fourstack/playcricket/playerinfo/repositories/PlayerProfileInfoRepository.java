package org.fourstack.playcricket.playerinfo.repositories;

import org.fourstack.playcricket.playerinfo.models.PlayerProfileInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerProfileInfoRepository extends JpaRepository<PlayerProfileInfo, String> {

}
