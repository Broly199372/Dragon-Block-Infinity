package com.dragonblockinfinity.race;

import com.dragonblockinfinity.fightstyle.FightStyleEnum;

/**
 * Raça Namekiano
 * Místicos e regenerativos com grande afinidade com Ki
 * 
 * Stats Base: Espírito alto, Vontade alta
 * Ki Máximo: 4500 base
 * Multiplicador de Ki: 1.8x
 * Estilo de Luta: Spiritualist (Ki e magia)
 */
public class Namekian implements IRace {
    
    private static final int MAX_KI_BASE = 4500;
    private static final double KI_MULTIPLIER = 1.8;
    private static final FightStyleEnum FIGHT_STYLE = FightStyleEnum.SPIRITUALIST;
    
    // Stats base
    private static final int BASE_STR = 9;
    private static final int BASE_CON = 10;
    private static final int BASE_DEX = 10;
    private static final int BASE_WILL = 14;
    private static final int BASE_SPI = 15;
    private static final int BASE_MND = 12;
    
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
        return "Namekiano";
    }
    
    @Override
    public String getRaceDescription() {
        return "Místicos e regenerativos com grande afinidade com Ki e magia";
    }
    
    @Override
    public int calculateMaxKi() {
        return (int)(MAX_KI_BASE * KI_MULTIPLIER);
    }
    
    @Override
    public String toString() {
        return "Namekian{" +
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
