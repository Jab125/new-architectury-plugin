package dev.architectury.plugin.mixin;

import dev.architectury.plugin.util.Platform;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = Platform.class, priority = -2147483648)
public class TransformerMixin {
}
