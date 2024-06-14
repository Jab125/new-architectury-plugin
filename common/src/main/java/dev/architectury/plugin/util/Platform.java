package dev.architectury.plugin.util;

import dev.architectury.plugin.annotations.ExpectPlatform;
import org.jetbrains.annotations.Nullable;

public class Platform {
	@ExpectPlatform
	public static String getLoaderName() {
		throw new AssertionError();
	}

	@ExpectPlatform
	public static @Nullable Mod getMod(String name) {
		throw new AssertionError();
	}

	@ExpectPlatform
	public static boolean isModLoaded(String name) {
		throw new AssertionError();
	}
}
