package org.fourstack.playcricket.playerinfo.utility;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class PlayerUniqueIdGenerator {

	@Async
	public CompletableFuture<String> generatePlayerUniqueId(String playerFirstName) {
		String uniqueId = "";
		if (playerFirstName != null) {
			String firstThreeLetters = (playerFirstName.length() > 3) ? playerFirstName.substring(0, 3)
					: playerFirstName;
			uniqueId = uniqueId.concat(firstThreeLetters);
		}

		return CompletableFuture.completedFuture(uniqueId.concat(getUniqueIdByStringManipulations()).toUpperCase());
	}

	private String getUniqueIdByStringManipulations() {
		LocalDateTime dateTime = LocalDateTime.now();

		String uniqueId = Integer.toHexString(dateTime.getYear()) 
				+ Integer.toHexString(dateTime.getMonthValue())
				+ Integer.toHexString(dateTime.getDayOfMonth()) 
				+ Integer.toHexString(dateTime.getHour())
				+ Integer.toHexString(dateTime.getMinute()) 
				+ Integer.toHexString(dateTime.getSecond())
				+ Integer.toHexString(dateTime.getNano());
		return uniqueId;

	}
}
