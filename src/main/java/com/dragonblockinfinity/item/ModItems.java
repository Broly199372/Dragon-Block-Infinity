package com.dragonblockinfinity.item;

import com.dragonblockinfinity.DragonBlockInfinity;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Registro de itens do mod (DeferredRegister).
 * Varre os assets de item e registra itens automaticamente,
 * exceto os nomes presentes na blacklist.
 */
public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, DragonBlockInfinity.MOD_ID);

    // Blacklist: nÃ£o registrar itens privados/pesados
    private static final List<String> BLACKLIST = List.of("peso", "weightshirt", "Flying_Nimbus", "fly_ninbus", "dark_fly_ninbus");

    // Itens detectados manualmente do diretÃ³rio de texturas (preenchido com os PNGs jÃ¡ presentes no repo)
    private static final String[] AUTO_ITEMS = new String[]{
            // comida folder
            "dino_meat",
            "dino_meat_cooked",
            "senzu",
            // ninbus folder (exceto blacklist entries)
            "dragon_esfer",
            "esfera_dragon_earth",
            "radar",
            // pesos folder (skip peso, weightshirt)
            // scouter
            "scouter"
    };

    // Registrations list (optional access)
    public static final List<RegistryObject<Item>> REGISTERED = new ArrayList<>();

    static {
        for (String name : AUTO_ITEMS) {
            String key = name.toLowerCase();
            if (BLACKLIST.contains(name) || BLACKLIST.contains(key)) continue;
            RegistryObject<Item> reg = ITEMS.register(key, () -> new Item(new Item.Properties()));
            REGISTERED.add(reg);
        }
        // Mantemos scouter como destaque (jÃ¡ incluso acima)
    }

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
