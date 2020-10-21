package org.fourstack.playcricket.playerinfo.controllers;

import static org.fourstack.playcricket.playerinfo.constants.PlayerInfoConstants.PAGE_NUMBER;
import static org.fourstack.playcricket.playerinfo.constants.PlayerInfoConstants.PAGE_SIZE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.fourstack.playcricket.playerinfo.models.PlayerProfileInfo;
import org.fourstack.playcricket.playerinfo.services.PlayerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/player-info")
public class PlayerInfoController {

	@Autowired
	private PlayerInfoService playerService;

	@ApiOperation(httpMethod = "GET", value = "API to fetch the PlayerProfileInfo(Pagination Enabled - default page - 0, size - 10)")
	@GetMapping("/players-profiles")
	public Page<PlayerProfileInfo> getAllPlayersProfiles(
			@RequestParam(value = "pageNum", required = false, defaultValue = "0") Integer pageNum,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
		pageNum = (pageNum == null) ? PAGE_NUMBER : pageNum;
		pageSize = (pageSize == null) ? PAGE_SIZE : pageSize;

		Pageable pageable = PageRequest.of(pageNum, pageSize);
		return playerService.fetchPlayersProfiles(pageable);

	}

	@GetMapping("/players-profiles/{playerId}")
	public PlayerProfileInfo getPlayerProfileById(@RequestParam(value = "playerId") String playerId) {
		return playerService.fetchPlayerById(playerId);
	}

	@ApiOperation(httpMethod = "POST", value = "API to consume PlayerProfileInfo and to save it to Database", hidden = true)
	@PostMapping(path = "/players", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public PlayerProfileInfo addPlayerProfiles(@RequestBody PlayerProfileInfo playerProfile) {
		return playerService.savePlayerProfile(playerProfile);
	}

}
