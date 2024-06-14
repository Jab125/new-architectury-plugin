package dev.architectury.plugin.util.neoforge;

import dev.architectury.plugin.util.Mod;
import net.neoforged.fml.ModContainer;

public class NeoForgeMod implements Mod {

	private final ModContainer modContainer;

	public NeoForgeMod(ModContainer modContainer) {
		this.modContainer = modContainer;
	}

	@Override
	public String id() {
		return modContainer.getModId();
	}

	@Override
	public String name() {
		return modContainer.getModInfo().getDisplayName();
	}

	@Override
	public String description() {
		return modContainer.getModInfo().getDescription();
	}

	@Override
	public String version() {
		return modContainer.getModInfo().getVersion().toString();
	}

	@Override
	public String toString() {
		return "NeoForgeMod{" +
			   "id=" + id() +
			   ",name=" + name() +
			   ",version=" + version() +
			   '}';
	}
}
