package org.fourstack.playcricket.rankinginfo.helpers;

import static org.fourstack.playcricket.rankinginfo.codetype.RankingArea.ODI;
import static org.fourstack.playcricket.rankinginfo.codetype.RankingArea.T20;
import static org.fourstack.playcricket.rankinginfo.codetype.RankingArea.TEST;
import static org.fourstack.playcricket.rankinginfo.codetype.RankingField.ALLROUND;
import static org.fourstack.playcricket.rankinginfo.codetype.RankingField.BAT;
import static org.fourstack.playcricket.rankinginfo.codetype.RankingField.BOWL;
import static org.fourstack.playcricket.rankinginfo.constants.RankingConstants.COMMA;
import static org.fourstack.playcricket.rankinginfo.constants.RankingConstants.DOT_SPACE;
import static org.fourstack.playcricket.rankinginfo.constants.RankingConstants.FORMAT;
import static org.fourstack.playcricket.rankinginfo.constants.RankingConstants.RANKING_FIELD;
import static org.fourstack.playcricket.rankinginfo.constants.RankingErrorConstants.MISSING_REQUIRED_FIELD;
import static org.fourstack.playcricket.rankinginfo.constants.RankingErrorConstants.PROVIDED_INVALID_DATA;
import static org.fourstack.playcricket.rankinginfo.constants.RankingErrorConstants.VALID_VALUES;

import org.fourstack.playcricket.rankinginfo.codetype.RankingArea;
import org.fourstack.playcricket.rankinginfo.codetype.RankingField;
import org.fourstack.playcricket.rankinginfo.models.IccRanking;
import org.springframework.stereotype.Component;

@Component
public class RankingInfoValidationHelper {

	public void validateRankingArea(RankingArea area) {
		if (area == null)
			throw new RuntimeException(MISSING_REQUIRED_FIELD + FORMAT);

		if (area != TEST && area != ODI && area != T20) {
			String validValues = TEST.name() + COMMA + ODI.name() + COMMA + T20.name();
			throw new RuntimeException(PROVIDED_INVALID_DATA + FORMAT + DOT_SPACE + VALID_VALUES + validValues);
		}
	}

	public void validateRankingField(RankingField field) {
		if (field == null)
			throw new RuntimeException(MISSING_REQUIRED_FIELD + RANKING_FIELD);

		if (field != BAT && field != BOWL && field != ALLROUND) {
			String validValues = BAT.name() + COMMA + BOWL.name() + COMMA + ALLROUND.name();
			throw new RuntimeException(PROVIDED_INVALID_DATA + RANKING_FIELD + DOT_SPACE + VALID_VALUES + validValues);
		}

	}
	
	public void validateIccRankingRequiredFields(IccRanking iccRanking) {
		iccRanking.getRankingInfo().stream().forEach(rankingInfo -> {
			validateRankingArea(rankingInfo.getFormat());
			
			rankingInfo.getStatus().stream().forEach(rankingStatus -> {
				validateRankingField(rankingStatus.getField());
			});
		});
	}
}
