package com.dragonblockinfinity.race;

import com.dragonblockinfinity.fightstyle.FightStyleEnum;

/**
 * Raça Android
 * Tecnológico e perfeito com grande poder constante
 * 
 * Stats Base: Força alta, Destreza alta
 * Ki Máximo: 6000 base (maior de todos!)
 * Multiplicador de Ki: 2.0x
 * Estilo de Luta: Warrior (combate direto)
 */
public class Android implements IRace {
    
    private static final int MAX_KI_BASE = 6000;
    private static final double KI_MULTIPLIER = 2.0;
    private static final FightStyleEnum FIGHT_STYLE = FightStyleEnum.WARRIOR;
    
    // Stats base
    private static final int BASE_STR = 16;
    private static final int BASE_CON = 13;
    private static final int BASE_DEX = 14;
    private static final int BASE_WILL = 9;
    private static final int BASE_SPI = 8;
    private static final int BASE_MND = 11;
    
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
        return "Android";
    }
    
    @Override
    public String getRaceDescription() {
        return "Tecnológico e perfeito com poder constante e altíssimo Ki máximo";
    }
    
    @Override
    public int calculateMaxKi() {
        return (int)(MAX_KI_BASE * KI_MULTIPLIER);
    }
    
    @Override
    public String toString() {
        return "Android{" +
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
