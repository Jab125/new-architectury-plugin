package dev.architectury.plugin.util;

public class InternalLoaderUtils {
	public static final Loader FABRIC = new Loader("fabric");
	public static final Loader FORGE = new Loader("forge");
	public static final Loader NEOFORGE = new Loader("neoforge");

	public static Loader getLoader() {
		if (classExists("dev.architectury.plugin.marker.FabricMarker")) return FABRIC;
		if (classExists("dev.architectury.plugin.marker.ForgeMarker")) return FORGE;
		if (classExists("dev.architectury.plugin.marker.NeoForgeMarker")) return NEOFORGE;
		throw new NullPointerException(); // TODO
	}

	private static boolean classExists(String clazz) {
		try {
			Class.forName(clazz);
			return true;
		} catch (ClassNotFoundException e) {
			return false;
		}
	}

	public record Loader(String packageName) {}
}
