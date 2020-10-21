package org.fourstack.playcricket.playerinfo.codetype;

public enum GenderType {
	MALE {
		@Override
		public String toString() {
			return "MALE";
		}
	},
	FEMALE {
		@Override
		public String toString() {
			return "FEMALE";
		}
	},
	OTHER {
		@Override
		public String toString() {
			return "OTHER";
		}
	}
}
