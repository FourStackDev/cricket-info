package org.fourstack.playcricket.bowlinginfo.models.data;

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
@Table(name = "bowling_info")
public class BowlingInfoData{
	
	@Id
	public String bowlingInfoId;
	private String format;
	private int matches;
	private int innings;
	private int balls;
	private int runs;
	private int maidens;
	private int wickets;
	private double average;
	private double economy;
	private double strikeRate;
	private String bestBowlingInInnings;
	private String bestBowlingInMatch;
	private int fourWicketHaul;
	private int fiveWicketHaul;
	private int tenWicketHaul;

}
