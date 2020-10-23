package org.fourstack.playcricket.battinginfo.helpers;

import static org.fourstack.playcricket.battinginfo.codetype.CricketFormat.ODI;
import static org.fourstack.playcricket.battinginfo.codetype.CricketFormat.T20;
import static org.fourstack.playcricket.battinginfo.codetype.CricketFormat.TEST;
import static org.fourstack.playcricket.battinginfo.constants.BattingInfoServiceConstants.*;

import org.fourstack.playcricket.battinginfo.codetype.CricketFormat;
import org.fourstack.playcricket.battinginfo.models.PlayerBattingInfo;
import org.springframework.stereotype.Component;

@Component
public class BattingInfoValidationHelper {

	public void validateCricketFormat(CricketFormat format) {
		if (format == null)
			throw new RuntimeException("Missing Require Field : " + FORMAT);

		if (format != TEST || format != ODI || format != T20)
			throw new RuntimeException("Invalid Data: " + FORMAT);
	}

	public void validatePlayerId(String playerId) {
		if (playerId == null || "".equalsIgnoreCase(playerId))
			throw new RuntimeException("Missing Required Field: " + PLAYER_ID);
	}

	public void validatePlayerBattingInfo(PlayerBattingInfo battinginfo) {
		validatePlayerId(battinginfo.getPlayerId());
		battinginfo.getBattingStatistics().stream().forEach(info -> validateCricketFormat(info.getFormat()));
	}
}
