package org.fourstack.playcricket.playerinfo.utility;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class PlayerUniqueIdGenerator {

	@Async("AsyncThreadPoolExecutor")
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

		StringBuffer uniqueId = new StringBuffer();
		uniqueId.append(Integer.toHexString(dateTime.getYear()))
				.append(Integer.toHexString(dateTime.getMonthValue()))
				.append(Integer.toHexString(dateTime.getDayOfMonth()))
				.append(Integer.toHexString(dateTime.getHour()))
				.append(Integer.toHexString(dateTime.getMinute()))
				.append(Integer.toHexString(dateTime.getSecond()))
				.append(Integer.toHexString(dateTime.getNano()));
		return uniqueId.toString();

	}
}
