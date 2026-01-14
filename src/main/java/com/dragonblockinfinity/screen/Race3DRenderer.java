package com.dragonblockinfinity.screen;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import com.dragonblockinfinity.race.RaceEnum;
import com.dragonblockinfinity.screen.CustomizationData;
import org.joml.Quaternionf;

/**
 * Renderizador 3D REAL para modelos das raÃ§as no menu de seleÃ§Ã£o
 */
public class Race3DRenderer {
    
    /**
     * Renderiza um modelo 3D texturizado da raÃ§a com renderizaÃ§Ã£o REAL
     */
    public static void renderRaceModel(GuiGraphics graphics, int x, int y, int width, int height,
                                       RaceEnum race, float rotationYaw, float rotationPitch) {
        PoseStack poseStack = graphics.pose();
        poseStack.pushPose();
        
        // Posicionar e escalar
        poseStack.translate(x + width / 2.0, y + height, 0);
        poseStack.scale(width / 32.0f, width / 32.0f, width / 32.0f);
        
        // RotaÃ§Ãµes
        poseStack.mulPose(Axis.XP.rotationDegrees(180.0f));
        poseStack.mulPose(Axis.YP.rotationDegrees(rotationYaw));
        poseStack.mulPose(Axis.XP.rotationDegrees(rotationPitch));
        
        // Obter player do cliente
        Minecraft mc = Minecraft.getInstance();
        if (mc.player != null) {
            EntityRenderDispatcher dispatcher = mc.getEntityRenderDispatcher();
            MultiBufferSource.BufferSource bufferSource = mc.renderBuffers().bufferSource();
            
            // Configurar iluminaÃ§Ã£o
            RenderSystem.setShaderLights(
                new org.joml.Vector3f(0.2f, 1.0f, -0.2f),
                new org.joml.Vector3f(-0.2f, 1.0f, 0.2f)
            );
            
            // Renderizar entidade (usando player como base)
            LivingEntity entity = mc.player;
            
            // TODO: Aqui vocÃª pode criar uma entidade customizada para cada raÃ§a
            // Por enquanto usa o player como referÃªncia
            dispatcher.render(entity, 0, 0, 0, 0, 0, poseStack, bufferSource, 15728880);
            bufferSource.endBatch();
        }
        
        poseStack.popPose();
        
        // Renderizar olhos por cima (overlay 2D)
        renderEyes(graphics, x, y, width, height, race);
    }
    
    /**
     * Renderiza apenas a textura da raÃ§a como sprite (fallback)
     */
    public static void renderRaceTexture(GuiGraphics graphics, int x, int y, int width, int height,
                                         RaceEnum race) {
        String textureFile = getTextureForRace(race);
        ResourceLocation texture = new ResourceLocation("dragonblockinfinity", "textures/entity/race/" + textureFile);
        graphics.blit(texture, x, y, 0, 0, width, height, width, height);
    }
    
    /**
     * Renderiza olhos com posiÃ§Ãµes especÃ­ficas por raÃ§a
     */
    private static void renderEyes(GuiGraphics graphics, int x, int y, int width, int height, RaceEnum race) {
        String eyeLeft = getEyeTexture(CustomizationData.getSelectedEyes(), true);
        String eyeRight = getEyeTexture(CustomizationData.getSelectedEyes(), false);
        
        if (eyeLeft == null || eyeRight == null) return;
        
        ResourceLocation eyeL = new ResourceLocation("dragonblockinfinity", "textures/entity/customizaÃ§Ã£o/eye/" + eyeLeft);
        ResourceLocation eyeR = new ResourceLocation("dragonblockinfinity", "textures/entity/customizaÃ§Ã£o/eye/" + eyeRight);
        
        // Obter offset de olhos por raÃ§a
        EyePosition eyePos = getEyePositionForRace(race, width, height);
        
        int centerX = x + width / 2;
        int centerY = y + height / 2;
        
        // PosiÃ§Ãµes dos olhos com offset customizado por raÃ§a
        int leftEyeX = centerX + eyePos.leftOffsetX;
        int rightEyeX = centerX + eyePos.rightOffsetX;
        int eyeY = centerY + eyePos.offsetY;
        
        graphics.blit(eyeL, leftEyeX, eyeY, 0, 0, eyePos.eyeWidth, eyePos.eyeHeight, eyePos.eyeWidth, eyePos.eyeHeight);
        graphics.blit(eyeR, rightEyeX, eyeY, 0, 0, eyePos.eyeWidth, eyePos.eyeHeight, eyePos.eyeWidth, eyePos.eyeHeight);
    }
    
    /**
     * Classe para armazenar posiÃ§Ã£o dos olhos
     */
    private static class EyePosition {
        int leftOffsetX;
        int rightOffsetX;
        int offsetY;
        int eyeWidth;
        int eyeHeight;
        
        EyePosition(int leftX, int rightX, int y, int w, int h) {
            this.leftOffsetX = leftX;
            this.rightOffsetX = rightX;
            this.offsetY = y;
            this.eyeWidth = w;
            this.eyeHeight = h;
        }
    }
    
    /**
     * ObtÃ©m posiÃ§Ã£o dos olhos customizada por raÃ§a
     */
    private static EyePosition getEyePositionForRace(RaceEnum race, int modelWidth, int modelHeight) {
        int baseEyeW = Math.max(8, modelWidth / 8);
        int baseEyeH = Math.max(8, modelHeight / 16);
        
        return switch (race) {
            case SAIYAN -> new EyePosition(
                -(modelWidth / 6) - baseEyeW / 2,  // Olho esquerdo
                (modelWidth / 6) - baseEyeW / 2,   // Olho direito
                -modelHeight / 4,                   // Altura (mais alto)
                baseEyeW,
                baseEyeH
            );
            
            case HALF -> new EyePosition(
                -(modelWidth / 6) - baseEyeW / 2,
                (modelWidth / 6) - baseEyeW / 2,
                -modelHeight / 4 + 2,               // Levemente mais baixo que Saiyan
                baseEyeW,
                baseEyeH
            );
            
            case HUMAN -> new EyePosition(
                -(modelWidth / 7) - baseEyeW / 2,   // Olhos mais juntos
                (modelWidth / 7) - baseEyeW / 2,
                -modelHeight / 4,
                baseEyeW,
                baseEyeH
            );
            
            case ARCONSIAN -> new EyePosition(
                -(modelWidth / 5) - baseEyeW / 2,   // Olhos mais afastados
                (modelWidth / 5) - baseEyeW / 2,
                -modelHeight / 3,                   // Mais alto na cabeÃ§a
                (int)(baseEyeW * 1.2),              // Olhos maiores
                (int)(baseEyeH * 1.2)
            );
            
            default -> new EyePosition(
                -(modelWidth / 6) - baseEyeW / 2,
                (modelWidth / 6) - baseEyeW / 2,
                -modelHeight / 4,
                baseEyeW,
                baseEyeH
            );
        };
    }
    
    /**
     * ObtÃ©m o arquivo de textura para cada raÃ§a
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
     * ObtÃ©m a textura do corpo para cada raÃ§a
     */
    public static String getBodyTextureForRace(RaceEnum race) {
        return switch (race) {
            case SAIYAN, HALF, HUMAN -> "musculosrace/musculos.png";
            case ARCONSIAN -> "musculosrace/musculos_arconsian.png";
            default -> "musculosrace/musculos.png";
        };
    }
    
    /**
     * ObtÃ©m textura dos olhos
     */
    private static String getEyeTexture(int index, boolean left) {
        if (index < 0) return null;
        return switch (index) {
            case 0 -> left ? "eye_1_left.png" : "eye_1_right.png";
            // TODO: Adicionar mais tipos quando tiver as texturas
            // case 1 -> left ? "eye_2_left.png" : "eye_2_right.png";
            // case 2 -> left ? "eye_3_left.png" : "eye_3_right.png";
            default -> left ? "eye_1_left.png" : "eye_1_right.png";
        };
    }
}
