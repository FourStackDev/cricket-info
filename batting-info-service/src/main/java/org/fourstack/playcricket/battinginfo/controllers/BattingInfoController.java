package org.fourstack.playcricket.battinginfo.controllers;

import static org.fourstack.playcricket.battinginfo.constants.BattingInfoServiceConstants.*;

import org.fourstack.playcricket.battinginfo.models.PlayerBattingInfo;
import org.fourstack.playcricket.battinginfo.services.BattingInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/v1/batting-service")
public class BattingInfoController {

	@Autowired
	private BattingInfoService battingInfoService;

	@GetMapping("/player-batting-statistics")
	public Page<PlayerBattingInfo> getPlayersBattingStatistics(
			@RequestParam(value = "pageNum", required = false, defaultValue = "0") Integer pageNum,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
		pageNum = (pageNum == null) ? DEFAULT_PAGE_NUM : pageNum;
		pageSize = (pageSize == null) ? DEFAULT_PAGE_SIZE : pageSize;

		Pageable pageable = PageRequest.of(pageNum, pageSize);
		return battingInfoService.getPlayersBattingStatistics(pageable);
	}

	@GetMapping("/player-batting-statistics/{id}")
	public PlayerBattingInfo getPlayerBattingStatisticsById(@PathVariable(value = "id") String id) {
		return battingInfoService.getPlayersBattingStatisticsById(id);
	}

	@GetMapping("/player/{player-id}/batting-statistics")
	public PlayerBattingInfo getPlayerBattingStatisticsByPlayerId(@PathVariable(value = "player-id") String playerId) {
		return battingInfoService.getPlayersBattingStatisticsByPlayerId(playerId);
	}
	
	@PostMapping("/player-batting-statistics")
	public void addPlayerBattingStatistics(@RequestBody PlayerBattingInfo battingInfo) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			System.out.println(mapper.writeValueAsString(battingInfo));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
