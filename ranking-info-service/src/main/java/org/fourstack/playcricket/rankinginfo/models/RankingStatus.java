package org.fourstack.playcricket.rankinginfo.models;

import org.fourstack.playcricket.rankinginfo.codetype.RankingField;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RankingStatus {
	private RankingField field;
	private int currentRanking;
	private int bestRanking;
}
