package com.dragonblockinfinity.handler;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

public class KeyBindings {
    public static final String CATEGORY = "Dragon Block Infinity";

    public static final KeyMapping OPEN_CUSTOMIZATION_KEY = new KeyMapping(
            "key.dragonblockinfinity.customization",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_C,
            CATEGORY
    );
}
