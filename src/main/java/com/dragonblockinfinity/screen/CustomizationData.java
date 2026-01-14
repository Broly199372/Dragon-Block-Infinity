package com.dragonblockinfinity.screen;

import com.dragonblockinfinity.race.RaceEnum;

/**
 * Dados simples de customização mantidos no cliente.
 */
public class CustomizationData {

    private static RaceEnum selectedRace = RaceEnum.SAIYAN;
    private static int selectedEyes = 0;
    private static int selectedColor = 0xFFFFCC88; // ARGB default (light race base)

    public static RaceEnum getSelectedRace() {
        return selectedRace;
    }

    public static void setSelectedRace(RaceEnum race) {
        selectedRace = race;
    }

    public static int getSelectedEyes() {
        return selectedEyes;
    }

    public static void setSelectedEyes(int idx) {
        selectedEyes = idx;
    }

    public static int getSelectedColor() {
        return selectedColor;
    }

    public static void setSelectedColor(int colorArgb) {
        selectedColor = colorArgb | 0xFF000000; // ensure alpha
    }
}
