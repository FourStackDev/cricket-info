package org.fourstack.playcricket.playerinfo.helpers;

import java.util.concurrent.CompletableFuture;

import org.fourstack.playcricket.playerinfo.models.common.MultiMediaDocument;
import org.fourstack.playcricket.playerinfo.repositories.MultiMediaDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MultiMediaDocumentHelper {
	@Autowired
	private MultiMediaDocumentRepository repository;

	public CompletableFuture<MultiMediaDocument> saveMultiMediaDocument(MultiMediaDocument document) {
		MultiMediaDocument savedDocument = repository.save(document);
		return CompletableFuture.completedFuture(savedDocument);
	}
}
