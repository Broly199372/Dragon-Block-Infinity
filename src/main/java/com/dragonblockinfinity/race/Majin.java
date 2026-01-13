package com.dragonblockinfinity.race;

import com.dragonblockinfinity.fightstyle.FightStyleEnum;

/**
 * Raça Majin
 * Mágica e poderosa com grande versatilidade
 * 
 * Stats Base: Vontade alta, Constituição alta
 * Ki Máximo: 5500 base
 * Multiplicador de Ki: 1.9x
 * Estilo de Luta: Spiritualist (magia)
 */
public class Majin implements IRace {
    
    private static final int MAX_KI_BASE = 5500;
    private static final double KI_MULTIPLIER = 1.9;
    private static final FightStyleEnum FIGHT_STYLE = FightStyleEnum.SPIRITUALIST;
    
    // Stats base
    private static final int BASE_STR = 12;
    private static final int BASE_CON = 14;
    private static final int BASE_DEX = 11;
    private static final int BASE_WILL = 15;
    private static final int BASE_SPI = 13;
    private static final int BASE_MND = 10;
    
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
        return "Majin";
    }
    
    @Override
    public String getRaceDescription() {
        return "Mágica e poderosa com grande versatilidade e poder mágico";
    }
    
    @Override
    public int calculateMaxKi() {
        return (int)(MAX_KI_BASE * KI_MULTIPLIER);
    }
    
    @Override
    public String toString() {
        return "Majin{" +
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
