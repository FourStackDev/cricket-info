package org.fourstack.playcricket.bowlinginfo;

import java.math.BigInteger;
import java.time.LocalDateTime;

public class Test {

	public static void main(String[] args) {
		// yyyymmddhhmmssSSS
		
		for (int i = 0 ; i < 50; i++) {
			System.out.println(generateHexStringFormDateTime());
		}

	}

	private static String generateHexStringFormDateTime() {

		LocalDateTime dateTime = LocalDateTime.now();
		System.out.println(dateTime);

		String str1 = Integer.toHexString(dateTime.getYear()) 
				+ Integer.toHexString(dateTime.getMonthValue())
				+ Integer.toHexString(dateTime.getDayOfMonth()) 
				+ Integer.toHexString(dateTime.getHour())
				+ Integer.toHexString(dateTime.getMinute()) 
				+ Integer.toHexString(dateTime.getSecond())
				+ Integer.toHexString(dateTime.getNano());

		return str1;
	}

}
