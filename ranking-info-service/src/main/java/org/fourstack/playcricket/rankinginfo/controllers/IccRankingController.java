package org.fourstack.playcricket.rankinginfo.controllers;

import static org.fourstack.playcricket.rankinginfo.constants.RankingConstants.PAGE_NUMBER;
import static org.fourstack.playcricket.rankinginfo.constants.RankingConstants.PAGE_SIZE;

import org.fourstack.playcricket.rankinginfo.models.IccRanking;
import org.fourstack.playcricket.rankinginfo.service.RankingInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/icc-rankings")
public class IccRankingController {

	@Autowired
	private RankingInfoService rankingService;

	@ApiOperation(httpMethod = "GET", value = "API to fetch page of Players ICC Rankings (Pagination Enabled - default page = 0, size = 10)")
	@GetMapping("/rankings")
	public Page<IccRanking> getAllIccRankings(
			@RequestParam(value = "pageNum", defaultValue = "0", required = false) Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
		pageNum = (pageNum == null) ? PAGE_NUMBER : pageNum;
		pageSize = (pageSize == null) ? PAGE_SIZE : pageSize;
		
		Pageable pageable = PageRequest.of(pageNum, pageSize);
		return rankingService.getIccRankingList(pageable);
	}

	@ApiOperation(httpMethod = "GET", value = "API to fetch Player ICC Rankings based on the id")
	@GetMapping("/{ranking-id}")
	public IccRanking getIccRankingById(@PathVariable("ranking-id") String rankingId) {
		return rankingService.getIccRankingById(rankingId);
	}

	@ApiOperation(httpMethod = "GET", value = "API to fetch Player ICC Rankings based on the playerId")
	@GetMapping("/{player-id}/ranking")
	public IccRanking getIccRankingByPlayerId(@PathVariable("player-id") String playerId) {
		return rankingService.getIccRankingByPlayerId(playerId);
	}
	
	@ApiOperation(httpMethod = "POST", value = "API to consume Player ICC Rankings and to save it to database")
	@PostMapping("/rankings")
	public ResponseEntity<IccRanking> addIccRanking(@RequestBody IccRanking ranking) {
		IccRanking savedIccRanking = rankingService.saveIccRanking(ranking);
		return new ResponseEntity<IccRanking>(savedIccRanking, HttpStatus.CREATED);
	}

}
