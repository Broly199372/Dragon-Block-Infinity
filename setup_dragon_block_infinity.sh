#!/bin/bash
# =============================================================
# Dragon-Block-Infinity — Setup Script
# Minecraft 1.21.1 | Fabric Loader 0.16.5 | Loom 1.7-SNAPSHOT
# Cole este script no terminal do seu Codespace e rode: bash setup_dragon_block_infinity.sh
# =============================================================

echo "🐉 Criando estrutura do Dragon-Block-Infinity..."

# ── Diretórios ───────────────────────────────────────────────
mkdir -p src/main/java/com/dragonblock/infinity/{items,blocks,effects,mixin}
mkdir -p src/client/java/com/dragonblock/infinity
mkdir -p src/main/resources/assets/dragon_block_infinity/{textures/item,models/item,lang}
mkdir -p gradle/wrapper

# ── gradle.properties ────────────────────────────────────────
cat << 'EOF' > gradle.properties
org.gradle.jvmargs=-Xmx1G
org.gradle.parallel=true

# Fabric Properties
minecraft_version=1.21.1
yarn_mappings=1.21.1+build.3
loader_version=0.16.5
loom_version=1.7-SNAPSHOT

# Mod Properties
mod_version=0.1.0
maven_group=com.dragonblock
archives_base_name=dragon-block-infinity

# Dependencies
fabric_version=0.92.2+1.21.1
EOF

# ── settings.gradle ──────────────────────────────────────────
cat << 'EOF' > settings.gradle
pluginManagement {
    repositories {
        maven {
            name = 'Fabric'
            url = 'https://maven.fabricmc.net/'
        }
        gradlePluginPortal()
    }
}

rootProject.name = 'dragon-block-infinity'
EOF

# ── build.gradle ─────────────────────────────────────────────
cat << 'EOF' > build.gradle
plugins {
    id 'fabric-loom' version "${loom_version}"
    id 'maven-publish'
}

version = project.mod_version
group = project.maven_group
base { archivesName = project.archives_base_name }

repositories {
    maven { url 'https://maven.fabricmc.net/' }
}

loom {
    splitEnvironmentSourceSets()
    mods {
        "dragon_block_infinity" {
            sourceSet sourceSets.main
            sourceSet sourceSets.client
        }
    }
}

dependencies {
    minecraft "com.mojang:minecraft:${project.minecraft_version}"
    mappings "net.fabricmc:yarn:${project.yarn_mappings}:v2"
    modImplementation "net.fabricmc:fabric-loader:${project.loader_version}"
    modImplementation "net.fabricmc.fabric-api:fabric-api:${project.fabric_version}"
}

processResources {
    inputs.property "version", project.version
    filesMatching("fabric.mod.json") {
        expand "version": inputs.properties.version
    }
}

tasks.withType(JavaCompile).configureEach {
    it.options.release = 21
}

java {
    withSourcesJar()
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

jar {
    from("LICENSE") {
        rename { "${it}_${project.archivesBaseName}" }
    }
}
EOF

# ── gradle/wrapper/gradle-wrapper.properties ─────────────────
cat << 'EOF' > gradle/wrapper/gradle-wrapper.properties
distributionBase=GRADLE_USER_HOME
distributionPath=wrapper/dists
distributionUrl=https\://services.gradle.org/distributions/gradle-8.8-bin.zip
zipStoreBase=GRADLE_USER_HOME
zipStorePath=wrapper/dists
EOF

# ── fabric.mod.json ──────────────────────────────────────────
cat << 'EOF' > src/main/resources/fabric.mod.json
{
  "schemaVersion": 1,
  "id": "dragon_block_infinity",
  "version": "${version}",
  "name": "Dragon Block Infinity",
  "description": "Mod de Dragon Ball para Minecraft 1.21.1 com Fabric",
  "authors": ["Broly199372"],
  "contact": {
    "sources": "https://github.com/Broly199372/Dragon-Block-Infinity"
  },
  "license": "MIT",
  "icon": "assets/dragon_block_infinity/icon.png",
  "environment": "*",
  "entrypoints": {
    "main": ["com.dragonblock.infinity.DragonBlockInfinity"],
    "client": ["com.dragonblock.infinity.DragonBlockInfinityClient"]
  },
  "mixins": ["dragon_block_infinity.mixins.json"],
  "depends": {
    "fabricloader": ">=0.16.5",
    "fabric-api": "*",
    "minecraft": "~1.21.1",
    "java": ">=21"
  }
}
EOF

# ── Mixin config ─────────────────────────────────────────────
cat << 'EOF' > src/main/resources/dragon_block_infinity.mixins.json
{
  "required": true,
  "minVersion": "0.8",
  "package": "com.dragonblock.infinity.mixin",
  "compatibilityLevel": "JAVA_21",
  "mixins": ["PlayerEntityMixin"],
  "client": [],
  "injectors": {
    "defaultRequire": 1
  }
}
EOF

# ── Classe principal ─────────────────────────────────────────
cat << 'EOF' > src/main/java/com/dragonblock/infinity/DragonBlockInfinity.java
package com.dragonblock.infinity;

import com.dragonblock.infinity.items.ModItems;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DragonBlockInfinity implements ModInitializer {
    public static final String MOD_ID = "dragon_block_infinity";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Dragon Block Infinity está carregando... KA-ME-HA-ME-HA!");
        ModItems.registerItems();
    }
}
EOF

# ── ModItems ─────────────────────────────────────────────────
cat << 'EOF' > src/main/java/com/dragonblock/infinity/items/ModItems.java
package com.dragonblock.infinity.items;

import com.dragonblock.infinity.DragonBlockInfinity;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItems {

    // ── 7 Esferas do Dragão ──────────────────────────────────
    public static final Item DRAGON_BALL_1 = register("dragon_ball_1",
            new Item(new Item.Settings().maxCount(1)));
    public static final Item DRAGON_BALL_2 = register("dragon_ball_2",
            new Item(new Item.Settings().maxCount(1)));
    public static final Item DRAGON_BALL_3 = register("dragon_ball_3",
            new Item(new Item.Settings().maxCount(1)));
    public static final Item DRAGON_BALL_4 = register("dragon_ball_4",
            new Item(new Item.Settings().maxCount(1)));
    public static final Item DRAGON_BALL_5 = register("dragon_ball_5",
            new Item(new Item.Settings().maxCount(1)));
    public static final Item DRAGON_BALL_6 = register("dragon_ball_6",
            new Item(new Item.Settings().maxCount(1)));
    public static final Item DRAGON_BALL_7 = register("dragon_ball_7",
            new Item(new Item.Settings().maxCount(1)));

    // ── Ki Gem (recurso de Ki) ───────────────────────────────
    public static final Item KI_GEM = register("ki_gem",
            new Item(new Item.Settings()));

    // ── Senzu Bean ──────────────────────────────────────────
    public static final Item SENZU_BEAN = register("senzu_bean",
            new Item(new Item.Settings().food(
                net.minecraft.item.FoodComponents.GOLDEN_APPLE
            )));

    private static Item register(String name, Item item) {
        return Registry.register(
            Registries.ITEM,
            Identifier.of(DragonBlockInfinity.MOD_ID, name),
            item
        );
    }

    public static void registerItems() {
        // Adiciona ao grupo "Itens Variados" do vanilla
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(KI_GEM);
            entries.add(SENZU_BEAN);
            entries.add(DRAGON_BALL_1);
            entries.add(DRAGON_BALL_2);
            entries.add(DRAGON_BALL_3);
            entries.add(DRAGON_BALL_4);
            entries.add(DRAGON_BALL_5);
            entries.add(DRAGON_BALL_6);
            entries.add(DRAGON_BALL_7);
        });

        DragonBlockInfinity.LOGGER.info("Itens do Dragon Block Infinity registrados!");
    }
}
EOF

# ── Cliente (entrypoint) ──────────────────────────────────────
cat << 'EOF' > src/client/java/com/dragonblock/infinity/DragonBlockInfinityClient.java
package com.dragonblock.infinity;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class DragonBlockInfinityClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        DragonBlockInfinity.LOGGER.info("Dragon Block Infinity (Cliente) carregado!");
    }
}
EOF

# ── Mixin: barra de Ki no player ─────────────────────────────
cat << 'EOF' > src/main/java/com/dragonblock/infinity/mixin/PlayerEntityMixin.java
package com.dragonblock.infinity.mixin;

import com.dragonblock.infinity.DragonBlockInfinity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {

    // Placeholder: futuramente injeta lógica de Ki aqui
    @Inject(method = "tick", at = @At("HEAD"))
    private void onTick(CallbackInfo ci) {
        // TODO: atualizar barra de Ki
    }
}
EOF

# ── lang/pt_br.json ──────────────────────────────────────────
cat << 'EOF' > src/main/resources/assets/dragon_block_infinity/lang/pt_br.json
{
  "item.dragon_block_infinity.dragon_ball_1": "Esfera do Dragão ★",
  "item.dragon_block_infinity.dragon_ball_2": "Esfera do Dragão ★★",
  "item.dragon_block_infinity.dragon_ball_3": "Esfera do Dragão ★★★",
  "item.dragon_block_infinity.dragon_ball_4": "Esfera do Dragão ★★★★",
  "item.dragon_block_infinity.dragon_ball_5": "Esfera do Dragão ★★★★★",
  "item.dragon_block_infinity.dragon_ball_6": "Esfera do Dragão ★★★★★★",
  "item.dragon_block_infinity.dragon_ball_7": "Esfera do Dragão ★★★★★★★",
  "item.dragon_block_infinity.ki_gem": "Gema de Ki",
  "item.dragon_block_infinity.senzu_bean": "Feijão Senzu"
}
EOF

cat << 'EOF' > src/main/resources/assets/dragon_block_infinity/lang/en_us.json
{
  "item.dragon_block_infinity.dragon_ball_1": "Dragon Ball ★",
  "item.dragon_block_infinity.dragon_ball_2": "Dragon Ball ★★",
  "item.dragon_block_infinity.dragon_ball_3": "Dragon Ball ★★★",
  "item.dragon_block_infinity.dragon_ball_4": "Dragon Ball ★★★★",
  "item.dragon_block_infinity.dragon_ball_5": "Dragon Ball ★★★★★",
  "item.dragon_block_infinity.dragon_ball_6": "Dragon Ball ★★★★★★",
  "item.dragon_block_infinity.dragon_ball_7": "Dragon Ball ★★★★★★★",
  "item.dragon_block_infinity.ki_gem": "Ki Gem",
  "item.dragon_block_infinity.senzu_bean": "Senzu Bean"
}
EOF

# ── Models (placeholder) ─────────────────────────────────────
for i in 1 2 3 4 5 6 7; do
cat << EOF > src/main/resources/assets/dragon_block_infinity/models/item/dragon_ball_${i}.json
{
  "parent": "item/generated",
  "textures": {
    "layer0": "dragon_block_infinity:item/dragon_ball_${i}"
  }
}
EOF
done

cat << 'EOF' > src/main/resources/assets/dragon_block_infinity/models/item/ki_gem.json
{
  "parent": "item/generated",
  "textures": { "layer0": "dragon_block_infinity:item/ki_gem" }
}
EOF

cat << 'EOF' > src/main/resources/assets/dragon_block_infinity/models/item/senzu_bean.json
{
  "parent": "item/generated",
  "textures": { "layer0": "dragon_block_infinity:item/senzu_bean" }
}
EOF

# ── Gradle wrapper script (Unix) ─────────────────────────────
cat << 'GRADLEW' > gradlew
#!/bin/sh
exec gradle "$@"
GRADLEW
chmod +x gradlew

echo ""
echo "✅ Estrutura criada com sucesso!"
echo ""
echo "📦 Próximos passos no terminal:"
echo "   1. gradle wrapper --gradle-version 8.8"
echo "   2. ./gradlew genSources    (gera fontes do MC)"
echo "   3. ./gradlew build         (compila o mod)"
echo ""
echo "🎨 Lembre de adicionar texturas PNG em:"
echo "   src/main/resources/assets/dragon_block_infinity/textures/item/"
echo ""
echo "🐉 KA-ME-HA-ME-HA! O mod está pronto para evoluir!"
EOF
