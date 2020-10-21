package org.fourstack.playcricket.playerinfo.models;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.fourstack.playcricket.playerinfo.codetype.BattingStyleType;
import org.fourstack.playcricket.playerinfo.codetype.BowlingStyleType;
import org.fourstack.playcricket.playerinfo.codetype.PlayerRoleType;
import org.fourstack.playcricket.playerinfo.models.common.MultiMediaDocument;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Entity
@Table(name = "player_profile_info")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlayerProfileInfo {

	@Id
	@Column(name = "player_id", nullable = false)
	@ApiModelProperty(hidden = true)
	private String playerId;

	@OneToOne
	private PlayerBasicInfo basicInfo;

	@OneToOne
	private MultiMediaDocument imageInfo;

	@Column(name = "roles")
	@Enumerated(EnumType.STRING)
	@ElementCollection
	private List<PlayerRoleType> roles;

	@Enumerated(EnumType.STRING)
	private BattingStyleType battingStyle;

	@Enumerated(EnumType.STRING)
	private BowlingStyleType bowlingStyle;
	
	@CreationTimestamp
	@Column(name = "created_date", nullable = false, updatable = false)
	private LocalDateTime createdDateTime;

	
	@UpdateTimestamp
	@Column(name = "updated_date", nullable = false, updatable = true)
	private LocalDateTime updatedDateTime;
}
