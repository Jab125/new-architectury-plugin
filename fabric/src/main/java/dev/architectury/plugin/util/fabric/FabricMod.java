package dev.architectury.plugin.util.fabric;

import dev.architectury.plugin.util.Mod;
import net.fabricmc.loader.api.ModContainer;

public class FabricMod implements Mod {

	private final ModContainer modContainer;

	public FabricMod(ModContainer modContainer) {
		this.modContainer = modContainer;
	}

	@Override
	public String id() {
		return modContainer.getMetadata().getId();
	}

	@Override
	public String name() {
		return modContainer.getMetadata().getName();
	}

	@Override
	public String description() {
		return modContainer.getMetadata().getDescription();
	}

	@Override
	public String version() {
		return modContainer.getMetadata().getVersion().getFriendlyString();
	}

	@Override
	public String toString() {
		return "FabricMod{" +
			   "id=" + id() +
			   ",name=" + name() +
			   ",version=" + version() +
			   '}';
	}
}
