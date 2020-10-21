package org.fourstack.playcricket.rankinginfo.models.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "ranking_info_data")
public class RankingInfoData {

	@Id
	private String rankingInfoId;
	private String area;
	private String field;
	private int currentRanking;
	private int bestRanking;

}
