package org.fourstack.playcricket.battinginfo.models.data;

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
@Table(name =  "batting_data")
public class BattingInfoData {

	@Id
	private String battingInfoId;
	private String format;
	private int matches;
	private int innings;
	private int runs;
	private int balls;
	private int highest;
	private double average;
	private double strikeRate;
	private int numberOfNotOuts;
	private int fours;
	private int sixes;
	private int ducks;
	private int noOfHalfCenturies;
	private int noOfCenturies;
	private int noOfDoubleCenturies;
	private int noOfTripleCenturies;
	private int noOfQuadrupleCenturies;
}
