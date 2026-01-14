package com.dragonblockinfinity.registry;

import com.dragonblockinfinity.block.ModBlocks;
import com.dragonblockinfinity.entity.ModEntities;
import com.dragonblockinfinity.item.ModItems;

/**
 * Classe central para registrar todos os componentes do mod
 */
public class ModRegistry {
    public static void register(net.minecraftforge.eventbus.api.IEventBus bus) {
        ModBlocks.register(bus);
        com.dragonblockinfinity.item.ModItems.register(bus);
        ModEntities.register();
    }
}
