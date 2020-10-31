package org.fourstack.playcricket.bowlinginfo.util;

import static org.fourstack.playcricket.bowlinginfo.constants.BowlingInfoConstants.BOWLING_INFO_ID_PREFIX;
import static org.fourstack.playcricket.bowlinginfo.constants.BowlingInfoConstants.HYPEN;

public class IdGenerationUtil {
	
	public static String generateBowlingInfoId(String playerId, String format) {
		return BOWLING_INFO_ID_PREFIX + format + HYPEN + playerId;
	}

	public static String generatePlayerBowlingInfoId(String playerId) {
		return BOWLING_INFO_ID_PREFIX + playerId;
	}

}
