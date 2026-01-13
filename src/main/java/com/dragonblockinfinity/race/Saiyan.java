package com.dragonblockinfinity.race;

import com.dragonblockinfinity.fightstyle.FightStyleEnum;

/**
 * Raça Saiyajin
 * Guerreiros natos com grande potencial de poder
 * 
 * Stats Base: Força alta, Constituição alta
 * Ki Máximo: 5000 base
 * Multiplicador de Ki: 1.5x
 * Estilo de Luta: Warrior (corpo a corpo)
 */
public class Saiyan implements IRace {
    
    private static final int MAX_KI_BASE = 5000;
    private static final double KI_MULTIPLIER = 1.5;
    private static final FightStyleEnum FIGHT_STYLE = FightStyleEnum.WARRIOR;
    
    // Stats base
    private static final int BASE_STR = 15;
    private static final int BASE_CON = 14;
    private static final int BASE_DEX = 12;
    private static final int BASE_WILL = 10;
    private static final int BASE_SPI = 10;
    private static final int BASE_MND = 8;
    
    @Override
    public int getMaxKiBase() {
        return MAX_KI_BASE;
    }
    
    @Override
    public double getKiMultiplier() {
        return KI_MULTIPLIER;
    }
    
    @Override
    public FightStyleEnum getPreferredFightStyle() {
        return FIGHT_STYLE;
    }
    
    @Override
    public int getBaseStrength() {
        return BASE_STR;
    }
    
    @Override
    public int getBaseConstitution() {
        return BASE_CON;
    }
    
    @Override
    public int getBaseDexterity() {
        return BASE_DEX;
    }
    
    @Override
    public int getBaseWillpower() {
        return BASE_WILL;
    }
    
    @Override
    public int getBaseSpirit() {
        return BASE_SPI;
    }
    
    @Override
    public int getBaseMind() {
        return BASE_MND;
    }
    
    @Override
    public String getRaceName() {
        return "Saiyajin";
    }
    
    @Override
    public String getRaceDescription() {
        return "Guerreiros natos com grande potencial de poder e transformações";
    }
    
    @Override
    public int calculateMaxKi() {
        return (int)(MAX_KI_BASE * KI_MULTIPLIER);
    }
    
    @Override
    public String toString() {
        return "Saiyan{" +
                "Ki=" + calculateMaxKi() +
                ", Estilo=" + FIGHT_STYLE.getDisplayName() +
                ", Stats=[Str=" + BASE_STR +
                ", Con=" + BASE_CON +
                ", Dex=" + BASE_DEX +
                ", Will=" + BASE_WILL +
                ", Spi=" + BASE_SPI +
                ", Mnd=" + BASE_MND +
                "]}";
    }
}
