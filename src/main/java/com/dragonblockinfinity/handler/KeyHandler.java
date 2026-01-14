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

import com.dragonblockinfinity.screen.MenuInicial1;

@Mod.EventBusSubscriber(modid = "dragonblockinfinity", bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class KeyHandler {

    public static final KeyMapping OPEN_MENU = new KeyMapping(
            "key.dragonblockinfinity.menu",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_G,
            "key.categories.dragonblockinfinity"
    );

    @SubscribeEvent
    public static void onRegisterKeys(RegisterKeyMappingsEvent event) {
        event.register(OPEN_MENU);
    }

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent event) {
        if (Minecraft.getInstance().screen == null && OPEN_MENU.consumeClick()) {
            Minecraft.getInstance().setScreen(new MenuInicial1());
        }
    }
}
