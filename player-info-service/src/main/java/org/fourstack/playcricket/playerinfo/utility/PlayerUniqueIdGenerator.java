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
		int randomNum = (int) Math.floor(Math.random() * 100);

		uniqueId.append(Integer.toHexString((int) Math.floor(Math.random() * 1000)))
				.append(Integer.toHexString(dateTime.getMinute())).append(Integer.toHexString(randomNum))
				.append(Integer.toHexString(dateTime.getSecond())).append(Integer.toHexString(randomNum))
				.append(Integer.toHexString(dateTime.getHour())).append(Integer.toHexString(randomNum))
				.append(Integer.toHexString(dateTime.getMonthValue())).append(Integer.toHexString(randomNum))
				.append(Integer.toHexString(dateTime.getDayOfMonth())).append((int) Math.floor(Math.random() * 100))
				.append(Integer.toHexString(dateTime.getYear())).append((int) Math.floor(Math.random() * 100));
		return uniqueId.toString();

	}
}
