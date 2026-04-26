package dev.rennivik.compactscoreboard.mixin;

import dev.rennivik.compactscoreboard.CompactScoreboardClient;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.numbers.StyledFormat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.CLIENT)
@Mixin(StyledFormat.class)
public class StyledFormatMixin {

	@Inject(method = "format", at = @At("HEAD"), cancellable = true)
	private void compact(int value, CallbackInfoReturnable<MutableComponent> cir) {
		if (!CompactScoreboardClient.REVEAL_KEY.isDown()) {
			long abs = Math.abs((long) value);

			String out;

			if (abs >= 1_000_000_000L) {
				out = format(value / 1_000_000_000.0) + "B";
			} else if (abs >= 1_000_000L) {
				out = format(value / 1_000_000.0) + "M";
			} else if (abs >= 1_000L) {
				out = format(value / 1_000.0) + "K";
			} else {
				out = String.valueOf(value);
			}

			cir.setReturnValue(Component.literal(out).withStyle(StyledFormat.SIDEBAR_DEFAULT.style()));
		}
	}

	@Unique
    private String format(double v) {
		if (v == (long) v) return String.valueOf((long) v);
		return String.format("%.1f", v);
	}
}