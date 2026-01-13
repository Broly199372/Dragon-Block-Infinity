package com.dragonblockinfinity.screen;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import com.dragonblockinfinity.race.RaceEnum;

/**
 * Renderizador 3D para modelos das raças no menu de seleção
 */
public class Race3DRenderer {
    
    /**
     * Renderiza um modelo 3D texturizado da raça
     */
    public static void renderRaceModel(PoseStack poseStack, int x, int y, int width, int height,
                                       RaceEnum race, float rotationYaw, float rotationPitch) {
        
        Minecraft minecraft = Minecraft.getInstance();
        
        poseStack.pushPose();
        poseStack.translate(x + width / 2, y + height / 2, 0);
        
        // Rotação
        poseStack.mulPose(com.mojang.math.Axis.YP.rotationDegrees(rotationYaw));
        poseStack.mulPose(com.mojang.math.Axis.XP.rotationDegrees(rotationPitch));
        
        poseStack.translate(-(width / 2), -(height / 2), 0);
        
        // Renderizar capa de cor baseado na raça
        String textureFile = getTextureForRace(race);
        ResourceLocation texture = new ResourceLocation("dragonblockinfinity", "textures/entity/" + textureFile);
        minecraft.getTextureManager().bindForSetup(texture);
        
        // Desenhar retângulo com textura
        fill(poseStack, x, y, x + width, y + height, 0xFF888888);
        
        poseStack.popPose();
    }
    
    /**
     * Renderiza apenas a textura da raça como sprite
     */
    public static void renderRaceTexture(PoseStack poseStack, int x, int y, int width, int height,
                                         RaceEnum race) {
        
        String textureFile = getTextureForRace(race);
        ResourceLocation texture = new ResourceLocation("dragonblockinfinity", "textures/entity/" + textureFile);
        
        Minecraft minecraft = Minecraft.getInstance();
        minecraft.getTextureManager().bindForSetup(texture);
        
        // Desenhar sprite com fundo
        fill(poseStack, x, y, x + width, y + height, 0xFF333333);
    }
    
    /**
     * Obtém o arquivo de textura para cada raça
     */
    private static String getTextureForRace(RaceEnum race) {
        return switch (race) {
            case SAIYAN -> "sayajin.png";
            case HALF -> "fsayajin.png";
            case HUMAN -> "humano.png";
            case ARCONSIAN -> "arconsian.png";
            default -> "sayajin.png";
        };
    }
    
    /**
     * Obtém a textura do corpo para cada raça
     */
    public static String getBodyTextureForRace(RaceEnum race) {
        return switch (race) {
            case SAIYAN, HALF, HUMAN -> "musculos.png"; // Todos usam musculos.png
            case ARCONSIAN -> "arconsian.png"; // Arconsian usa sua própria
            default -> "musculos.png";
        };
    }
    
    /**
     * Obtém textura auxiliar se existir
     */
    public static String getAuxTextureForRace(RaceEnum race) {
        return switch (race) {
            case SAIYAN, HALF, HUMAN -> "a_a.png"; // Usar a_a.png para roupas/aura
            case ARCONSIAN -> "mmenu_base.png"; // Arconsian usa base
            default -> "a_a.png";
        };
    }
    
    /**
     * Preenche um retângulo com cor (função auxiliar)
     */
    private static void fill(PoseStack poseStack, int x1, int y1, int x2, int y2, int color) {
        fill(poseStack, x1, y1, x2, y2, color);
    }
}
