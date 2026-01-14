package com.dragonblockinfinity.block;

import com.dragonblockinfinity.DragonBlockInfinity;
import com.dragonblockinfinity.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * Classe para registro de blocos customizados do Dragon Block Infinity
 */
public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, DragonBlockInfinity.MOD_ID);

        public static final RegistryObject<Block> DIRTY_STONE = BLOCKS.register("dirty_stone",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.5f, 6.0f).sound(SoundType.STONE)));

        // namek_stone removed per user request (uses vanilla stone)

    public static void register(IEventBus bus) {
        // Registrar blocos
        BLOCKS.register(bus);

        // Registrar BlockItems para aparecer no inventário — adiciona ao DeferredRegister de itens
        ModItems.ITEMS.register("dirty_stone", () -> new BlockItem(DIRTY_STONE.get(), new Item.Properties()));
    }
}

