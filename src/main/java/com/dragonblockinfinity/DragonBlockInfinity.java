package com.dragonblockinfinity;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import com.dragonblockinfinity.registry.ModRegistry;
import com.dragonblockinfinity.screen.ModScreens;
import com.dragonblockinfinity.handler.KeyHandler;

@Mod("dragonblockinfinity")
public class DragonBlockInfinity {

    public static final String MOD_ID = "dragonblockinfinity";

   public DragonBlockInfinity() {
      // Pega o barramento de eventos do Forge corretamente
      IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

      // Registra eventos do mod
      modEventBus.addListener(this::commonSetup);
      modEventBus.addListener(this::clientSetup);

      // Registra os componentes do mod
      ModRegistry.register(modEventBus);
      ModScreens.register();

      System.out.println("Dragon Block Infinity carregado com sucesso!");
   }

   private void commonSetup(final FMLCommonSetupEvent event) {
      // ConfiguraÃ§Ãµes gerais do mod (execuÃ§Ã£o do lado servidor/ambos)
   }

   private void clientSetup(final FMLClientSetupEvent event) {
      // InicializaÃ§Ãµes especÃ­ficas do cliente
   }
}
