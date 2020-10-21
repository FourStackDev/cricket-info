package org.fourstack.playcricket.rankinginfo.models;

import java.util.List;

import org.fourstack.playcricket.rankinginfo.codetype.RankingArea;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RankingInfo {
	private RankingArea format;
	private List<RankingStatus> status;
}
