package org.fourstack.playcricket.playerinfo.codetype;

public enum BattingStyleType {
	LEFT_HANDED {
		@Override
		public String toString() {
			return "LEFT_HANDED_BATSMAN";
		}
	},
	RIGHT_HANDED {
		@Override
		public String toString() {
			return "RIGHT_HANDED_BATSMAN";
		}
	}

}
