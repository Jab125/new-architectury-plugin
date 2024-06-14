package dev.architectury.plugin.neoforge;

import net.neoforged.fml.common.Mod;

import dev.architectury.plugin.ExampleMod;

@Mod(ExampleMod.MOD_ID)
public final class ExampleModNeoForge {
    public ExampleModNeoForge() {
        // Run our common setup.
        ExampleMod.init();
    }
}
