package dev.rennivik.compactscoreboard;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;
import org.lwjgl.glfw.GLFW;

public class CompactScoreboardClient implements ClientModInitializer {
    public static final KeyMapping.Category CATEGORY = KeyMapping.Category.register(
            Identifier.fromNamespaceAndPath("compact-scoreboard", "category")
    );

    public static final KeyMapping REVEAL_KEY = KeyMappingHelper.registerKeyMapping(
            new KeyMapping(
                    "key.compact-scoreboard.reveal",
                    InputConstants.Type.KEYSYM,
                    GLFW.GLFW_KEY_LEFT_ALT,
                    CATEGORY
            ));

    @Override
    public void onInitializeClient() {

    }
}
