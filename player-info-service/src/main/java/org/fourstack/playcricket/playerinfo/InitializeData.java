package org.fourstack.playcricket.playerinfo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.fourstack.playcricket.playerinfo.codetype.BattingStyleType;
import org.fourstack.playcricket.playerinfo.codetype.BowlingStyleType;
import org.fourstack.playcricket.playerinfo.codetype.GenderType;
import org.fourstack.playcricket.playerinfo.codetype.PlayerRoleType;
import org.fourstack.playcricket.playerinfo.helpers.PlayerProfileInfoHelper;
import org.fourstack.playcricket.playerinfo.models.PlayerBasicInfo;
import org.fourstack.playcricket.playerinfo.models.PlayerProfileInfo;
import org.fourstack.playcricket.playerinfo.models.common.MultiMediaDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitializeData implements CommandLineRunner {

	@Autowired
	private PlayerProfileInfoHelper helper;

	@Override
	public void run(String... args) throws Exception {
		List<PlayerProfileInfo> playersList = getPlayersList();
		for (PlayerProfileInfo player : playersList) {
			helper.savePlayerProfile(player);
		}

	}

	public List<PlayerProfileInfo> getPlayersList() {

		List<PlayerProfileInfo> playerList = new ArrayList<>();
		for (int i = 0; i < 1000; i++) {
			PlayerProfileInfo player1 = new PlayerProfileInfo();
			PlayerBasicInfo basic1 = new PlayerBasicInfo();
			basic1.setFirstName("Virat");
			basic1.setLastName("Kohli");
			basic1.setCountry("India");
			basic1.setDateOfBirth(LocalDate.of(1988, 11, 5));
			basic1.setGender(GenderType.MALE);
			basic1.setNickName("Kohli");
			basic1.setPlaceOfBirth("Delhi");

			MultiMediaDocument image1 = new MultiMediaDocument();
			image1.setFileName("virat-image" + i + ".jpeg");
			image1.setFileType("jpg");

			player1.setBasicInfo(basic1);
			player1.setImageInfo(image1);
			player1.setRoles(Arrays.asList(PlayerRoleType.BATSMAN));
			player1.setBattingStyle(BattingStyleType.RIGHT_HANDED);
			player1.setBowlingStyle(BowlingStyleType.RIGHT_ARM_MEDIUM);

			PlayerProfileInfo player2 = new PlayerProfileInfo();
			PlayerBasicInfo basic2 = new PlayerBasicInfo();
			basic2.setFirstName("Ravindra");
			basic2.setLastName("Jadeja");
			basic2.setCountry("India");
			basic2.setDateOfBirth(LocalDate.of(1988, 12, 6));
			basic2.setGender(GenderType.MALE);
			basic2.setNickName("Jaddu");
			basic2.setPlaceOfBirth("Saurashtra");

			MultiMediaDocument image2 = new MultiMediaDocument();
			image2.setFileName("jaddu-image" + i + ".jpeg");
			image2.setFileType("jpg");

			player2.setBasicInfo(basic2);
			player2.setImageInfo(image2);
			player2.setRoles(Arrays.asList(PlayerRoleType.BOWLER, PlayerRoleType.ALL_ROUNDER));
			player2.setBattingStyle(BattingStyleType.LEFT_HANDED);
			player2.setBowlingStyle(BowlingStyleType.LEFT_ARM_ORTHODOX);

			PlayerProfileInfo player3 = new PlayerProfileInfo();
			PlayerBasicInfo basic3 = new PlayerBasicInfo();
			basic3.setFirstName("AB");
			basic3.setMiddleName("de");
			basic3.setLastName("Villiers");
			basic3.setCountry("South Africa");
			basic3.setDateOfBirth(LocalDate.of(1984, 2, 17));
			basic3.setGender(GenderType.MALE);
			basic3.setNickName("ABD");
			basic3.setPlaceOfBirth("Pretoria");

			MultiMediaDocument image3 = new MultiMediaDocument();
			image3.setFileName("abd-image" + i + ".jpg");
			image3.setFileType("jpg");

			player3.setBasicInfo(basic3);
			player3.setImageInfo(image3);
			player3.setRoles(Arrays.asList(PlayerRoleType.BATSMAN, PlayerRoleType.WICKET_KEEPER));
			player3.setBattingStyle(BattingStyleType.RIGHT_HANDED);
			player3.setBowlingStyle(BowlingStyleType.RIGHT_ARM_MEDIUM);

			playerList.add(player1);
			playerList.add(player2);
			playerList.add(player3);
		}

		return playerList;
	}

}
