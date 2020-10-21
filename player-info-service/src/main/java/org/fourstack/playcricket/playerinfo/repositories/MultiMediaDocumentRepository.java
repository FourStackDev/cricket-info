package org.fourstack.playcricket.playerinfo.repositories;

import org.fourstack.playcricket.playerinfo.models.common.MultiMediaDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MultiMediaDocumentRepository extends JpaRepository<MultiMediaDocument, Long> {

}
