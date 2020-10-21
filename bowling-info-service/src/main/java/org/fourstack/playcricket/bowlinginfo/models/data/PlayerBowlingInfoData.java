package org.fourstack.playcricket.bowlinginfo.models.data;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "player_bowling_data")
public class PlayerBowlingInfoData {

	@Id
	private String playerBowlingInfoId;
	
	@Column(name = "playerId", unique = true)
	private String playerId;

	@OneToMany
	private List<BowlingInfoData> statistics;

	@CreationTimestamp
	@Column(name = "created_date", nullable = false, updatable = false)
	private LocalDateTime createdDate;

	@UpdateTimestamp
	@Column(name = "updated_date", nullable = false, updatable = true)
	private LocalDateTime updatedDate;
}
