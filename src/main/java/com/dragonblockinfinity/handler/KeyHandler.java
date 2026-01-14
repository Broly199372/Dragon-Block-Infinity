package com.dragonblockinfinity.handler;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.TickEvent.ClientTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;
import com.dragonblockinfinity.screen.CustomizationScreen;

/**
 * Classe para manipular eventos de teclado
 */
@Mod.EventBusSubscriber(modid = "dragonblockinfinity", bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class KeyHandler {

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent event) {
        if (Minecraft.getInstance().screen == null && KeyBindings.OPEN_CUSTOMIZATION_KEY.consumeClick()) {
            Minecraft.getInstance().setScreen(new CustomizationScreen());
        }
    }
}

