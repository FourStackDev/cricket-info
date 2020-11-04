package org.fourstack.playcricket.battinginfo.util;

import static org.fourstack.playcricket.battinginfo.constants.BattingInfoServiceConstants.BATTING_INFO_ID_PREFIX;
import static org.fourstack.playcricket.battinginfo.constants.BattingInfoServiceConstants.HYPEN;

public class BattingServiceIdGenerationUtil {

	public static String generatePlayerBattingInfoId(String playerId) {
		return BATTING_INFO_ID_PREFIX + playerId;
	}

	public static String generateBattingInfoId(String playerId, String format) {
		return BATTING_INFO_ID_PREFIX + format + HYPEN + playerId;
	}

}
