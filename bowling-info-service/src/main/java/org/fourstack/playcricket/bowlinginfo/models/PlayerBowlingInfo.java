package org.fourstack.playcricket.bowlinginfo.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlayerBowlingInfo {
	
	@ApiModelProperty(hidden = true)
	private String bowlingInfoId;
	private String playerId;
	private List<BowlingInfo> bowlingStatistics;
}
