package org.fourstack.playcricket.bowlinginfo.helpers;

import static org.fourstack.playcricket.bowlinginfo.constants.BowlingInfoConstants.*;
import static org.fourstack.playcricket.bowlinginfo.codetype.CricketFormat.*;

import org.fourstack.playcricket.bowlinginfo.codetype.CricketFormat;
import org.fourstack.playcricket.bowlinginfo.models.PlayerBowlingInfo;
import org.springframework.stereotype.Component;

@Component
public class BowlingInfoValidationHelper {

	public void validateCricketFormat(CricketFormat format) {
		if (format == null)
			throw new RuntimeException("Missing Required Field: " + FORMAT);

		if (format != TEST && format != ODI && format != T20)
			throw new RuntimeException("Invalid Data: " + FORMAT);
	}

	public void validatePlayerId(String playerId) {
		if (playerId == null || "".equalsIgnoreCase(playerId))
			throw new RuntimeException("Missing Required Field: " + PLAYER_ID);
	}

	public void validatePlayerBowlingInfo(PlayerBowlingInfo playerBowlingInfo) {
		validatePlayerId(playerBowlingInfo.getPlayerId());

		playerBowlingInfo.getBowlingStatistics().stream()
				.forEach(bowlingInfo -> validateCricketFormat(bowlingInfo.getFormat()));
	}
}
