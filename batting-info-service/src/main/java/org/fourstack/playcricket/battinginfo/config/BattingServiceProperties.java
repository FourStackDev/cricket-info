package org.fourstack.playcricket.battinginfo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class BattingServiceProperties {
	
	@Value("${PATCH_METHOD_TIMEOUT}")
	private int timeOut;
	
	@Value("${player.info.check-palayer-exists}")
	private String urlCheckIfPlayerExistsForPlayerId;
}
