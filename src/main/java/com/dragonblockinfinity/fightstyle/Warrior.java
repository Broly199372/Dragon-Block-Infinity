package com.dragonblockinfinity.fightstyle;

/**
 * Estilo Guerreiro
 * Focado em forÃ§a e resistÃªncia corporal
 * 
 * Stats Base:
 * - Str: 14 (muito alto)
 * - Con: 13 (alto)
 * - Dex: 10 (mÃ©dio)
 * - Will: 9 (baixo)
 * - Spi: 8 (muito baixo)
 * - Mnd: 9 (baixo)
 * 
 * Multiplicadores:
 * - Str: 2.2x (bÃ´nus mÃ¡ximo)
 * - Con: 2.0x (bÃ´nus mÃ¡ximo)
 * - Dex: 1.2x
 * - Mnd: 0.8x (penalidade)
 * - Will: 0.8x (penalidade)
 * - Spi: 0.5x (penalidade mÃ¡xima)
 */
public class Warrior implements IFightStyle {
    
    // Stats base
    private static final int BASE_STR = 14;
    private static final int BASE_CON = 13;
    private static final int BASE_DEX = 10;
    private static final int BASE_WILL = 9;
    private static final int BASE_SPI = 8;
    private static final int BASE_MND = 9;
    
    // Multiplicadores
    private static final double STR_MULT = 2.2;
    private static final double CON_MULT = 2.0;
    private static final double DEX_MULT = 1.2;
    private static final double WILL_MULT = 0.8;
    private static final double SPI_MULT = 0.5;
    private static final double MND_MULT = 0.8;
    
    @Override
    public double getStrengthMultiplier() {
        return STR_MULT;
    }
    
    @Override
    public double getConstitutionMultiplier() {
        return CON_MULT;
    }
    
    @Override
    public double getDexterityMultiplier() {
        return DEX_MULT;
    }
    
    @Override
    public double getWillMultiplier() {
        return WILL_MULT;
    }
    
    @Override
    public double getSpiritMultiplier() {
        return SPI_MULT;
    }
    
    @Override
    public double getMindMultiplier() {
        return MND_MULT;
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
    public String getStyleName() {
        return "Guerreiro";
    }
    
    @Override
    public String getStyleDescription() {
        return "Focado em forÃ§a e resistÃªncia corporal. Excelente em combate corpo a corpo mas fraco em Ki.";
    }
    
    @Override
    public int calculateBP(int str, int con, int dex, int will, int spi, int mnd) {
        return (int)(
            (str * STR_MULT) +
            (con * CON_MULT) +
            (dex * DEX_MULT) +
            (will * WILL_MULT) +
            (spi * SPI_MULT) +
            (mnd * MND_MULT)
        );
    }
    
    @Override
    public String toString() {
        return "Warrior{" +
                "Stats Base: Str=" + BASE_STR +
                ", Con=" + BASE_CON +
                ", Dex=" + BASE_DEX +
                ", Will=" + BASE_WILL +
                ", Spi=" + BASE_SPI +
                ", Mnd=" + BASE_MND +
                "\nMultiplicadores: Str=" + STR_MULT +
                ", Con=" + CON_MULT +
                ", Dex=" + DEX_MULT +
                ", Will=" + WILL_MULT +
                ", Spi=" + SPI_MULT +
                ", Mnd=" + MND_MULT +
                '}';
    }
}
