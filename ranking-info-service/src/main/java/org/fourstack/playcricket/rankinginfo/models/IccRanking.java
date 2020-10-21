package org.fourstack.playcricket.rankinginfo.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IccRanking {
	private String rankingId;
	private String playerId;
	private List<RankingInfo> rankingInfo;

}
