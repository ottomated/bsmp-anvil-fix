package net.ottomated.anvilfix;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AnvilFixMod implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("bsmp-anvil-fix");

	@Override
	public void onInitialize() {
		LOGGER.info("BSMP Anvil Fix initialized");
	}
}
