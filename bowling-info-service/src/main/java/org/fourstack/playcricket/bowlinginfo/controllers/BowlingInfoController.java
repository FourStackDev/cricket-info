package org.fourstack.playcricket.bowlinginfo.controllers;

import static org.fourstack.playcricket.bowlinginfo.constants.BowlingInfoConstants.*;

import org.fourstack.playcricket.bowlinginfo.models.PlayerBowlingInfo;
import org.fourstack.playcricket.bowlinginfo.services.BowlingInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/bowling-service")
public class BowlingInfoController {

	@Autowired
	private BowlingInfoService bowlingService;

	@GetMapping("/bowling-statistics")
	public Page<PlayerBowlingInfo> getPlayersBowlingStatistics(
			@RequestParam(value = "pageNum", required = false) Integer pageNum,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) {
		pageNum = (pageNum == null) ? DEFAULT_PAGE_NUM : pageNum;
		pageSize = (pageSize == null) ? DEFAULT_PAGE_SIZE : pageSize;

		Pageable pageable = PageRequest.of(pageNum, pageSize);
		return bowlingService.getPlayersBowlingStatisticsPage(pageable);
	}

	@GetMapping("/bowling-statistics/{id}")
	public PlayerBowlingInfo getPlayersBowlingStatisticsById(@PathVariable("id") String id) {
		return bowlingService.getPlayerBowlingStatisticsById(id);
	}

	@GetMapping("/bowler/{player-id}/statistics")
	public PlayerBowlingInfo getPlayersBowlingStatisticsByPlayerId(@PathVariable("player-id") String playerId) {
		return bowlingService.getPlayerBowlingStatisticsByPlayerId(playerId);
	}

}
