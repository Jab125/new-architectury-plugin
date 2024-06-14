package dev.architectury.plugin.util.fabric;

import dev.architectury.plugin.annotations.ExpectPlatform;
import dev.architectury.plugin.util.Mod;
import net.fabricmc.loader.api.FabricLoader;
import org.jetbrains.annotations.Nullable;

public class PlatformImpl {
	public static String getLoaderName() {
		return "fabric";
	}

	public static @Nullable Mod getMod(String name) {
		return FabricLoader.getInstance().getModContainer(name).map(FabricMod::new).orElse(null);
	}

	public static boolean isModLoaded(String name) {
		return FabricLoader.getInstance().isModLoaded(name);
	}
}
