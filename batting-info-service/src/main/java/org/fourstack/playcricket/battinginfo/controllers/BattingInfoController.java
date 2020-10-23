package org.fourstack.playcricket.battinginfo.controllers;

import static org.fourstack.playcricket.battinginfo.constants.BattingInfoServiceConstants.DEFAULT_PAGE_NUM;
import static org.fourstack.playcricket.battinginfo.constants.BattingInfoServiceConstants.DEFAULT_PAGE_SIZE;

import org.fourstack.playcricket.battinginfo.models.BattingInfo;
import org.fourstack.playcricket.battinginfo.models.PlayerBattingInfo;
import org.fourstack.playcricket.battinginfo.services.BattingInfoService;
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
@RequestMapping("/api/v1/batting-service")
public class BattingInfoController {

	@Autowired
	private BattingInfoService battingInfoService;

	@ApiOperation(httpMethod = "GET", value = "API to fetch page of Players Batting Statistics (Pagination Enabled - default page = 0, size = 10)")
	@GetMapping("/player-batting-statistics")
	public Page<PlayerBattingInfo> getPlayersBattingStatistics(
			@RequestParam(value = "pageNum", required = false, defaultValue = "0") Integer pageNum,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
		pageNum = (pageNum == null) ? DEFAULT_PAGE_NUM : pageNum;
		pageSize = (pageSize == null) ? DEFAULT_PAGE_SIZE : pageSize;

		Pageable pageable = PageRequest.of(pageNum, pageSize);
		return battingInfoService.getPlayersBattingStatistics(pageable);
	}

	@ApiOperation(httpMethod = "GET", value = "API to fetch Player Batting Statistics based on the id")
	@GetMapping("/player-batting-statistics/{id}")
	public PlayerBattingInfo getPlayerBattingStatisticsById(@PathVariable(value = "id") String id) {
		return battingInfoService.getPlayersBattingStatisticsById(id);
	}

	@ApiOperation(httpMethod = "GET", value = "API to fetch Player Batting Statistics based on the playerId")
	@GetMapping("/player/{player-id}/batting-statistics")
	public PlayerBattingInfo getPlayerBattingStatisticsByPlayerId(@PathVariable(value = "player-id") String playerId) {
		return battingInfoService.getPlayersBattingStatisticsByPlayerId(playerId);
	}

	@ApiOperation(httpMethod = "POST", value = "API to consume Player Batting Statistics and to save it to Database")
	@PostMapping("/player-batting-statistics")
	public ResponseEntity<PlayerBattingInfo> addPlayerBattingStatistics(@RequestBody PlayerBattingInfo battingInfo) {
		PlayerBattingInfo savedBattingInfo = battingInfoService.savePlayersBattingStatistics(battingInfo);
		return new ResponseEntity<PlayerBattingInfo>(savedBattingInfo, HttpStatus.CREATED);
	}

	@ApiOperation(httpMethod = "PATCH", value = "API to Patch the Batting Statistics")
	@PatchMapping("/player-batting-statistics/{player-id}")
	public ResponseEntity<PlayerBattingInfo> patchBattingInfo(@PathVariable("player-id") String playerId,
			@RequestBody BattingInfo battingInfo) {
		PlayerBattingInfo updatedStatistics = battingInfoService
				.updateBattingInfoForPlayerBattingStatistics(battingInfo, playerId);
		return new ResponseEntity<PlayerBattingInfo>(updatedStatistics, HttpStatus.OK);
	}

}
