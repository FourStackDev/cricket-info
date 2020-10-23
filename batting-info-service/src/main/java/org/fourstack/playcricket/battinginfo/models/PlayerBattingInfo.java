package org.fourstack.playcricket.battinginfo.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlayerBattingInfo {

	@ApiModelProperty(hidden = true)
	private String battingInfoId;
	private String playerId;
	private List<BattingInfo> battingStatistics;

}
