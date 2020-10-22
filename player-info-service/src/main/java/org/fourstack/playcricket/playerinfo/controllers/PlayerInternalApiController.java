package org.fourstack.playcricket.playerinfo.controllers;

import org.fourstack.playcricket.playerinfo.services.PlayerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/internal/player-info")
public class PlayerInternalApiController {

	@Autowired
	private PlayerInfoService service;

	@GetMapping(value = "/{player-id}/exists")
	public ResponseEntity<Boolean> checkIfthePlayerExistsOrNot(@PathVariable("player-id") String playerId) {
		boolean isPlayerExist = service.isPlayerProfileExistsOrNot(playerId);
		if (isPlayerExist)
			return new ResponseEntity<Boolean>(isPlayerExist, HttpStatus.OK);
		else
			return new ResponseEntity<Boolean>(isPlayerExist, HttpStatus.NOT_FOUND);
	}
}
