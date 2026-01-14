package com.dragonblockinfinity.race;

import com.dragonblockinfinity.fightstyle.FightStyleEnum;

/**
 * RaÃ§a Humano
 * AdaptÃ¡vel e determinado, grande potencial de aprendizado
 * 
 * Stats Base: Equilibrados
 * Ki MÃ¡ximo: 3000 base
 * Multiplicador de Ki: 1.0x
 * Estilo de Luta: Martial Arts (equilibrado)
 */
public class Human implements IRace {
    
    private static final int MAX_KI_BASE = 3000;
    private static final double KI_MULTIPLIER = 1.0;
    private static final FightStyleEnum FIGHT_STYLE = FightStyleEnum.MARTIAL_ARTS;
    
    // Stats base
    private static final int BASE_STR = 11;
    private static final int BASE_CON = 11;
    private static final int BASE_DEX = 11;
    private static final int BASE_WILL = 11;
    private static final int BASE_SPI = 11;
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
        return "Humano";
    }
    
    @Override
    public String getRaceDescription() {
        return "AdaptÃ¡vel e determinado com grande potencial de aprendizado";
    }
    
    @Override
    public int calculateMaxKi() {
        return (int)(MAX_KI_BASE * KI_MULTIPLIER);
    }
    
    @Override
    public String toString() {
        return "Human{" +
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
