package dev.architectury.plugin.util.neoforge;

import dev.architectury.plugin.util.Mod;
import net.neoforged.fml.ModList;
import org.jetbrains.annotations.Nullable;

public class PlatformImpl {
	public static String getLoaderName() {
		return "neoforge";
	}

	public static @Nullable Mod getMod(String id) {
		return ModList.get().getModContainerById(id).map(NeoForgeMod::new).orElse(null);
	}

	public static boolean isModLoaded(String id) {
		return ModList.get().isLoaded(id);
	}
}
