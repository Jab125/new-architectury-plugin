package dev.architectury.plugin;

import dev.architectury.plugin.util.InternalLoaderUtils;
import dev.architectury.plugin.util.Platform;
import net.minecraft.client.Minecraft;

public final class ExampleMod {
    public static final String MOD_ID = "architectury_plugin";

    public static void init() {
        // Write common init code here.
        System.out.println(InternalLoaderUtils.getLoader());
        String loaderName = Platform.getLoaderName();
        System.out.println(loaderName);
        System.out.println("Fabric API Loaded: " + Platform.isModLoaded("fabric-api"));
        System.out.println("Fabric API: " + Platform.getMod("fabric-api"));
        System.out.println("NeoForge Loaded: " + Platform.isModLoaded("neoforge"));
        System.out.println("NeoForge: " + Platform.getMod("neoforge"));
        Minecraft instance = Minecraft.getInstance();
    }
}
