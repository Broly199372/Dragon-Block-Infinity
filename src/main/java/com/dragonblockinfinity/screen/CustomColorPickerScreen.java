package com.dragonblockinfinity.screen;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.util.function.IntConsumer;

public class CustomColorPickerScreen extends Screen {

    private final Screen parent;
    private final IntConsumer callback;
    private int r,g,b;

    private int paletteX, paletteY, paletteW = 220, paletteH = 20;
    private int sliderW = 220, sliderH = 12;

    public CustomColorPickerScreen(Screen parent, IntConsumer callback, int initialArgb) {
        super(Component.literal("Selecionar cor"));
        this.parent = parent;
        this.callback = callback;
        this.r = (initialArgb >> 16) & 0xFF;
        this.g = (initialArgb >> 8) & 0xFF;
        this.b = (initialArgb) & 0xFF;
    }

    @Override
    protected void init() {
        int midX = this.width / 2;
        int midY = this.height / 2;

        int btnW = 60, btnH = 20;
        this.addRenderableWidget(Button.builder(Component.literal("OK"), btn -> {
            int color = (0xFF << 24) | (r << 16) | (g << 8) | b;
            callback.accept(color);
            Minecraft.getInstance().setScreen(parent);
        }).bounds(midX - btnW - 10, midY + 80, btnW, btnH).build());

        this.addRenderableWidget(Button.builder(Component.literal("Cancelar"), btn -> {
            Minecraft.getInstance().setScreen(parent);
        }).bounds(midX + 10, midY + 80, btnW, btnH).build());

        paletteX = (this.width - paletteW) / 2;
        paletteY = midY - 10 - paletteH - 40; // posiÃ§Ã£o acima dos sliders
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
        if (button == 0) {
            handleSliderDrag((int)mouseX, (int)mouseY);
            return true;
        }
        return super.mouseDragged(mouseX, mouseY, button, dragX, dragY);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0) {
            handleSliderDrag((int)mouseX, (int)mouseY);
            return true;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    private void handleSliderDrag(int mouseX, int mouseY) {
        int midY = this.height / 2;
        int rY = midY - 20;
        int gY = midY + 0;
        int bY = midY + 20;
        if (mouseY >= rY && mouseY <= rY + sliderH && mouseX >= paletteX && mouseX <= paletteX + sliderW) {
            r = Math.max(0, Math.min(255, (mouseX - paletteX) * 255 / sliderW));
        } else if (mouseY >= gY && mouseY <= gY + sliderH && mouseX >= paletteX && mouseX <= paletteX + sliderW) {
            g = Math.max(0, Math.min(255, (mouseX - paletteX) * 255 / sliderW));
        } else if (mouseY >= bY && mouseY <= bY + sliderH && mouseX >= paletteX && mouseX <= paletteX + sliderW) {
            b = Math.max(0, Math.min(255, (mouseX - paletteX) * 255 / sliderW));
        }
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        // Fundo escuro semi-transparente
        graphics.fill(0, 0, this.width, this.height, 0x77000000);
        int midX = this.width / 2;
        int midY = this.height / 2;

        // Painel central escuro
        int panelW = Math.max(320, paletteW + 80);
        int panelH = 260;
        int panelX = midX - panelW / 2;
        int panelY = midY - panelH / 2;
        graphics.fill(panelX, panelY, panelX + panelW, panelY + panelH, 0xCC000000);

        graphics.drawCenteredString(this.font, Component.literal("Selecionar cor base da raÃ§a"), midX, panelY + 8, 0xFFFFFF);

        // Desenhar paleta hue -> rainbow (dentro do painel)
        for (int i = 0; i < paletteW; i++) {
            float h = i / (float) paletteW;
            int col = 0xFF000000 | hsvToRgb(h, 1f, 1f);
            graphics.fill(paletteX + i, paletteY, paletteX + i + 1, paletteY + paletteH, col);
        }

        // Sliders R/G/B
        int rY = midY - 20 + 20;
        int gY = midY + 0 + 20;
        int bY = midY + 20 + 20;

        // Background bars
        graphics.fill(paletteX - 1, rY - 1, paletteX + sliderW + 1, rY + sliderH + 1, 0xFF333333);
        graphics.fill(paletteX - 1, gY - 1, paletteX + sliderW + 1, gY + sliderH + 1, 0xFF333333);
        graphics.fill(paletteX - 1, bY - 1, paletteX + sliderW + 1, bY + sliderH + 1, 0xFF333333);

        // Filled portions
        graphics.fill(paletteX, rY, paletteX + (r * sliderW / 255), rY + sliderH, (0xFF << 24) | (r << 16));
        graphics.fill(paletteX, gY, paletteX + (g * sliderW / 255), gY + sliderH, (0xFF << 24) | (g << 8));
        graphics.fill(paletteX, bY, paletteX + (b * sliderW / 255), bY + sliderH, (0xFF << 24) | b);

        // Labels
        graphics.drawString(this.font, "R: " + r, paletteX + paletteW + 10, rY, 0xFFFFFF);
        graphics.drawString(this.font, "G: " + g, paletteX + paletteW + 10, gY, 0xFFFFFF);
        graphics.drawString(this.font, "B: " + b, paletteX + paletteW + 10, bY, 0xFFFFFF);

        // Preview
        // Preview grande 64x64 centralizado dentro do painel
        int previewW = 64;
        int previewH = 64;
        int previewX = midX - previewW / 2;
        int previewY = panelY + 40;
        int previewColor = (0xFF << 24) | (r << 16) | (g << 8) | b;
        graphics.fill(previewX - 2, previewY - 2, previewX + previewW + 2, previewY + previewH + 2, 0xFF222222);
        graphics.fill(previewX, previewY, previewX + previewW, previewY + previewH, previewColor);

        super.render(graphics, mouseX, mouseY, partialTicks);
    }

    private int hsvToRgb(float h, float s, float v) {
        int i = (int) Math.floor(h * 6);
        float f = h * 6 - i;
        float p = v * (1 - s);
        float q = v * (1 - f * s);
        float t = v * (1 - (1 - f) * s);
        float r1 = 0, g1 = 0, b1 = 0;
        switch (i % 6) {
            case 0: r1 = v; g1 = t; b1 = p; break;
            case 1: r1 = q; g1 = v; b1 = p; break;
            case 2: r1 = p; g1 = v; b1 = t; break;
            case 3: r1 = p; g1 = q; b1 = v; break;
            case 4: r1 = t; g1 = p; b1 = v; break;
            case 5: r1 = v; g1 = p; b1 = q; break;
        }
        int ri = (int) (r1 * 255) & 0xFF;
        int gi = (int) (g1 * 255) & 0xFF;
        int bi = (int) (b1 * 255) & 0xFF;
        return (ri << 16) | (gi << 8) | bi;
    }
}
