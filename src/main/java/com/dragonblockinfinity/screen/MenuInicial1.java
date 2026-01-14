package com.dragonblockinfinity.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import com.dragonblockinfinity.race.RaceEnum;
import com.dragonblockinfinity.screen.Race3DRenderer;
import com.dragonblockinfinity.screen.CustomizationData;
import com.dragonblockinfinity.screen.CustomColorPickerScreen;

import java.util.List;

/**
 * Menu Inicial 1 - Customização Completa de Personagem
 * 
 * Layout:
 * - Esquerda (128px): Modelo 3D do player girando com mouse
 * - Direita: Customização (raça, cores, olhos, etc)
 */
public class MenuInicial1 extends Screen {
    
    // ===== TEXTURAS =====
    private static final ResourceLocation MENU_BASE = new ResourceLocation("dragonblockinfinity", "textures/gui/menus/menu_base.png");
    private static final ResourceLocation SETA_LEFT = new ResourceLocation("dragonblockinfinity", "textures/gui/buttoes/seta_left.png");
    private static final ResourceLocation SETA_RIGHT = new ResourceLocation("dragonblockinfinity", "textures/gui/buttoes/seta_right.png");
    private static final ResourceLocation BTN_X = new ResourceLocation("dragonblockinfinity", "textures/gui/buttoes/x.png");
    
    // ===== DIMENSÕES DO MENU =====
    private static final int MENU_WIDTH = 256;
    private static final int MENU_HEIGHT = 200;
    private int menuX, menuY;
    
    // ===== PREVIEW 3D =====
    private static final int PREVIEW_WIDTH = 128;
    private static final int PREVIEW_HEIGHT = 160;
    private int previewX, previewY;
    private float modelYaw = 0f;
    private double lastMouseX = 0;
    private boolean dragging = false;
    
    // ===== SELEÇÕES =====
    private final List<RaceEnum> races = List.of(RaceEnum.values());
    private int raceIndex = 0;
    private int eyeIndex = 0;
    private int skinColor = 0xFFFFCC88;
    private int leftEyeColor = 0xFF000000;
    private int rightEyeColor = 0xFF000000;
    
    // ===== POSIÇÕES DOS BOTÕES =====
    private static final int BTN_SIZE = 12;
    private static final int COLOR_BTN_SIZE = 16;
    private static final int SPACING = 20;
    
    public MenuInicial1() {
        super(Component.literal("Customização de Personagem"));
        this.raceIndex = races.indexOf(CustomizationData.getSelectedRace());
        this.eyeIndex = CustomizationData.getSelectedEyes();
        this.skinColor = CustomizationData.getSelectedColor();
    }
    
    @Override
    protected void init() {
        super.init();
        
        // Centralizar menu
        this.menuX = (this.width - MENU_WIDTH) / 2;
        this.menuY = (this.height - MENU_HEIGHT) / 2;
        
        // Área de preview (lado esquerdo)
        this.previewX = menuX + 12;
        this.previewY = menuY + 20;
        
        // ===== LADO DIREITO - CUSTOMIZAÇÃO =====
        int rightSideX = menuX + PREVIEW_WIDTH + 24; // Começa depois do preview
        int currentY = menuY + 20;
        
        // ===== 1. RAÇA =====
        addRenderableWidget(new ImageButton(
            rightSideX, currentY, BTN_SIZE, BTN_SIZE,
            0, 0, 0, SETA_LEFT, BTN_SIZE, BTN_SIZE,
            btn -> changeRace(-1)
        ));
        
        addRenderableWidget(new ImageButton(
            rightSideX + 100, currentY, BTN_SIZE, BTN_SIZE,
            0, 0, 0, SETA_RIGHT, BTN_SIZE, BTN_SIZE,
            btn -> changeRace(1)
        ));
        
        currentY += SPACING;
        
        // Botão cor da pele
        addRenderableWidget(Button.builder(
            Component.literal(""),
            btn -> openColorPicker(ColorTarget.SKIN)
        ).bounds(rightSideX + 20, currentY, COLOR_BTN_SIZE, COLOR_BTN_SIZE).build());
        
        currentY += SPACING + 5;
        
        // ===== 2. CABELO (futuro - botões vazios) =====
        addRenderableWidget(new ImageButton(
            rightSideX, currentY, BTN_SIZE, BTN_SIZE,
            0, 0, 0, SETA_LEFT, BTN_SIZE, BTN_SIZE,
            btn -> {} // Futuro: mudar cabelo
        ));
        
        addRenderableWidget(new ImageButton(
            rightSideX + 100, currentY, BTN_SIZE, BTN_SIZE,
            0, 0, 0, SETA_RIGHT, BTN_SIZE, BTN_SIZE,
            btn -> {} // Futuro: mudar cabelo
        ));
        
        currentY += SPACING;
        
        // Botão cor de cabelo
        addRenderableWidget(Button.builder(
            Component.literal(""),
            btn -> {} // Futuro: cor de cabelo
        ).bounds(rightSideX + 20, currentY, COLOR_BTN_SIZE, COLOR_BTN_SIZE).build());
        
        currentY += SPACING + 5;
        
        // ===== 3. NARIZ (futuro - botões vazios) =====
        addRenderableWidget(new ImageButton(
            rightSideX, currentY, BTN_SIZE, BTN_SIZE,
            0, 0, 0, SETA_LEFT, BTN_SIZE, BTN_SIZE,
            btn -> {} // Futuro: mudar nariz
        ));
        
        addRenderableWidget(new ImageButton(
            rightSideX + 100, currentY, BTN_SIZE, BTN_SIZE,
            0, 0, 0, SETA_RIGHT, BTN_SIZE, BTN_SIZE,
            btn -> {} // Futuro: mudar nariz
        ));
        
        currentY += SPACING + 5;
        
        // ===== 4. BOCA (futuro - botões vazios) =====
        addRenderableWidget(new ImageButton(
            rightSideX, currentY, BTN_SIZE, BTN_SIZE,
            0, 0, 0, SETA_LEFT, BTN_SIZE, BTN_SIZE,
            btn -> {} // Futuro: mudar boca
        ));
        
        addRenderableWidget(new ImageButton(
            rightSideX + 100, currentY, BTN_SIZE, BTN_SIZE,
            0, 0, 0, SETA_RIGHT, BTN_SIZE, BTN_SIZE,
            btn -> {} // Futuro: mudar boca
        ));
        
        currentY += SPACING + 5;
        
        // ===== 5. OLHOS =====
        addRenderableWidget(new ImageButton(
            rightSideX, currentY, BTN_SIZE, BTN_SIZE,
            0, 0, 0, SETA_LEFT, BTN_SIZE, BTN_SIZE,
            btn -> changeEyes(-1)
        ));
        
        addRenderableWidget(new ImageButton(
            rightSideX + 100, currentY, BTN_SIZE, BTN_SIZE,
            0, 0, 0, SETA_RIGHT, BTN_SIZE, BTN_SIZE,
            btn -> changeEyes(1)
        ));
        
        currentY += SPACING;
        
        // Botão cor olho esquerdo
        addRenderableWidget(Button.builder(
            Component.literal(""),
            btn -> openColorPicker(ColorTarget.LEFT_EYE)
        ).bounds(rightSideX + 10, currentY, COLOR_BTN_SIZE, COLOR_BTN_SIZE).build());
        
        // Botão cor olho direito
        addRenderableWidget(Button.builder(
            Component.literal(""),
            btn -> openColorPicker(ColorTarget.RIGHT_EYE)
        ).bounds(rightSideX + 35, currentY, COLOR_BTN_SIZE, COLOR_BTN_SIZE).build());
        
        // ===== BOTÃO APLICAR/CONFIRMAR =====
        addRenderableWidget(Button.builder(
            Component.literal("Confirmar"),
            btn -> applyAndClose()
        ).bounds(menuX + MENU_WIDTH - 70, menuY + MENU_HEIGHT - 30, 60, 20).build());
    }
    
    // ===== MÉTODOS DE MUDANÇA =====
    private void changeRace(int delta) {
        raceIndex = (raceIndex + delta) % races.size();
        if (raceIndex < 0) raceIndex += races.size();
    }
    
    private void changeEyes(int delta) {
        eyeIndex = (eyeIndex + delta) % 4; // 4 tipos de olhos
        if (eyeIndex < 0) eyeIndex += 4;
    }
    
    private void applyAndClose() {
        CustomizationData.setSelectedRace(races.get(raceIndex));
        CustomizationData.setSelectedEyes(eyeIndex);
        CustomizationData.setSelectedColor(skinColor);
        onClose();
    }
    
    // ===== PALETA DE CORES =====
    private enum ColorTarget {
        SKIN, HAIR, LEFT_EYE, RIGHT_EYE
    }
    
    private void openColorPicker(ColorTarget target) {
        int currentColor = switch (target) {
            case SKIN -> skinColor;
            case LEFT_EYE -> leftEyeColor;
            case RIGHT_EYE -> rightEyeColor;
            default -> 0xFFFFFFFF;
        };
        
        Minecraft.getInstance().setScreen(new CustomColorPickerScreen(
            this,
            color -> {
                switch (target) {
                    case SKIN -> skinColor = color;
                    case LEFT_EYE -> leftEyeColor = color;
                    case RIGHT_EYE -> rightEyeColor = color;
                }
            },
            currentColor
        ));
    }
    
    // ===== MOUSE EVENTS =====
    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
        if (button == 0 && dragging) {
            double delta = mouseX - lastMouseX;
            modelYaw += delta * 0.8f;
            lastMouseX = mouseX;
            return true;
        }
        return super.mouseDragged(mouseX, mouseY, button, dragX, dragY);
    }
    
    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0) {
            // Detectar clique na área de preview
            if (mouseX >= previewX && mouseX <= previewX + PREVIEW_WIDTH &&
                mouseY >= previewY && mouseY <= previewY + PREVIEW_HEIGHT) {
                dragging = true;
                lastMouseX = mouseX;
                return true;
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }
    
    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        if (button == 0) {
            dragging = false;
            return true;
        }
        return super.mouseReleased(mouseX, mouseY, button);
    }
    
    // ===== RENDER =====
    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        // Fundo escuro
        graphics.fill(0, 0, this.width, this.height, 0x77000000);
        
        // Menu base
        graphics.blit(MENU_BASE, menuX, menuY, 0, 0, MENU_WIDTH, MENU_HEIGHT, MENU_WIDTH, MENU_HEIGHT);
        
        // ===== PREVIEW 3D (ESQUERDA) =====
        // Fundo do preview
        graphics.fill(previewX - 2, previewY - 2, previewX + PREVIEW_WIDTH + 2, previewY + PREVIEW_HEIGHT + 2, 0xFF1E1E1E);
        
        // Renderizar modelo 3D
        Race3DRenderer.renderRaceModel(
            graphics,
            previewX, previewY,
            PREVIEW_WIDTH, PREVIEW_HEIGHT,
            races.get(raceIndex),
            modelYaw, 0f
        );
        
        // ===== LABELS DO LADO DIREITO =====
        int rightSideX = menuX + PREVIEW_WIDTH + 24;
        int currentY = menuY + 20;
        
        // Raça
        String raceName = races.get(raceIndex).getDisplayName();
        graphics.drawString(this.font, "Raça: " + raceName, rightSideX + 14, currentY + 2, 0xFFFFFF);
        currentY += SPACING;
        
        // Preview cor da pele
        graphics.fill(rightSideX + 20, currentY, rightSideX + 20 + COLOR_BTN_SIZE, currentY + COLOR_BTN_SIZE, skinColor);
        currentY += SPACING + 5;
        
        // Cabelo (futuro)
        currentY += SPACING;
        currentY += SPACING + 5;
        
        // Nariz (futuro)
        currentY += SPACING + 5;
        
        // Boca (futuro)
        currentY += SPACING + 5;
        
        // Olhos
        graphics.drawString(this.font, "Olho: Tipo " + (eyeIndex + 1), rightSideX + 14, currentY + 2, 0xFFFFFF);
        currentY += SPACING;
        
        // Preview cores dos olhos
        graphics.fill(rightSideX + 10, currentY, rightSideX + 10 + COLOR_BTN_SIZE, currentY + COLOR_BTN_SIZE, leftEyeColor);
        graphics.fill(rightSideX + 35, currentY, rightSideX + 35 + COLOR_BTN_SIZE, currentY + COLOR_BTN_SIZE, rightEyeColor);
        
        // Renderizar botões
        super.render(graphics, mouseX, mouseY, partialTicks);
    }
    
    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
