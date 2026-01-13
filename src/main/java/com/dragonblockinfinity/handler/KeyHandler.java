package com.dragonblockinfinity.handler;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

/**
 * Classe para manipular eventos de teclado
 */
@Mod.EventBusSubscriber(modid = "dragonblockinfinity", bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class KeyHandler {
    
    public static final String CATEGORY = "Dragon Block Infinity";
    
    public static final KeyMapping OPEN_CUSTOMIZATION_KEY = new KeyMapping(
            "key.dragonblockinfinity.customization",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_C,
            CATEGORY
    );
    
    @SubscribeEvent
    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(OPEN_CUSTOMIZATION_KEY);
    }
}

