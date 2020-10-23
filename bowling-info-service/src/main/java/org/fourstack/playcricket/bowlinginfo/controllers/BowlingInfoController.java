package org.fourstack.playcricket.bowlinginfo.controllers;

import static org.fourstack.playcricket.bowlinginfo.constants.BowlingInfoConstants.DEFAULT_PAGE_NUM;
import static org.fourstack.playcricket.bowlinginfo.constants.BowlingInfoConstants.DEFAULT_PAGE_SIZE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.fourstack.playcricket.bowlinginfo.models.BowlingInfo;
import org.fourstack.playcricket.bowlinginfo.models.PlayerBowlingInfo;
import org.fourstack.playcricket.bowlinginfo.services.BowlingInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/bowling-service")
public class BowlingInfoController {

	@Autowired
	private BowlingInfoService bowlingService;

	@ApiOperation(httpMethod = "GET", value = "API to fetch page of Players Bowling Statistics (Pagination Enabled - default page = 0, size = 10)")
	@GetMapping("/bowling-statistics")
	public Page<PlayerBowlingInfo> getPlayersBowlingStatistics(
			@RequestParam(value = "pageNum", required = false, defaultValue = "0") Integer pageNum,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
		pageNum = (pageNum == null) ? DEFAULT_PAGE_NUM : pageNum;
		pageSize = (pageSize == null) ? DEFAULT_PAGE_SIZE : pageSize;

		Pageable pageable = PageRequest.of(pageNum, pageSize);
		return bowlingService.getPlayersBowlingStatisticsPage(pageable);
	}

	@ApiOperation(httpMethod = "GET", value = "API to fetch Player Bowling Statistics based on the id")
	@GetMapping("/bowling-statistics/{id}")
	public PlayerBowlingInfo getPlayersBowlingStatisticsById(@PathVariable("id") String id) {
		return bowlingService.getPlayerBowlingStatisticsById(id);
	}

	@ApiOperation(httpMethod = "GET", value = "API to fetch the PLayer Bowling Statistics based on the playerId")
	@GetMapping("/bowler/{player-id}/statistics")
	public PlayerBowlingInfo getPlayersBowlingStatisticsByPlayerId(@PathVariable("player-id") String playerId) {
		return bowlingService.getPlayerBowlingStatisticsByPlayerId(playerId);
	}

	@ApiOperation(httpMethod = "POST", value = "API to consume the Player bowling statistics and to save it to database")
	@PostMapping("/bowler/statistics")
	public ResponseEntity<PlayerBowlingInfo> savePlayerBowlingInformation(@RequestBody PlayerBowlingInfo bowlingInfo) {
		PlayerBowlingInfo savedBowlingInfo = bowlingService.savePlayerBowlingStatistics(bowlingInfo);
		return new ResponseEntity<PlayerBowlingInfo>(savedBowlingInfo, HttpStatus.CREATED);
	}

	@ApiOperation(httpMethod = "PATCH", value = "API to patch the Bowler Statistics")
	@PatchMapping(value = "/bowler/{player-id}/statistics", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<PlayerBowlingInfo> patchBowlingStatistics(@PathVariable("player-id") String playerId,
			@RequestBody BowlingInfo bowlingInfo) {
		PlayerBowlingInfo patchedBowler = bowlingService.patchBowlingStatisticsToPlayer(playerId, bowlingInfo);
		return new ResponseEntity<PlayerBowlingInfo>(patchedBowler, HttpStatus.OK);

	}

}
