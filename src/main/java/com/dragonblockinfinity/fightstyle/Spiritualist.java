package com.dragonblockinfinity.fightstyle;

/**
 * Estilo Espiritualista
 * Focado em Ki e ataques mÃ¡gicos
 * 
 * Stats Base:
 * - Str: 8 (baixo)
 * - Con: 9 (baixo)
 * - Dex: 10 (mÃ©dio)
 * - Will: 13 (alto)
 * - Spi: 14 (muito alto)
 * - Mnd: 11 (acima da mÃ©dia)
 * 
 * Multiplicadores:
 * - Spi: 2.2x (bÃ´nus mÃ¡ximo)
 * - Will: 2.0x (bÃ´nus mÃ¡ximo)
 * - Dex: 1.2x
 * - Mnd: 1.0x
 * - Con: 0.8x (penalidade)
 * - Str: 0.5x (penalidade mÃ¡xima)
 */
public class Spiritualist implements IFightStyle {
    
    // Stats base
    private static final int BASE_STR = 8;
    private static final int BASE_CON = 9;
    private static final int BASE_DEX = 10;
    private static final int BASE_WILL = 13;
    private static final int BASE_SPI = 14;
    private static final int BASE_MND = 11;
    
    // Multiplicadores
    private static final double STR_MULT = 0.5;
    private static final double CON_MULT = 0.8;
    private static final double DEX_MULT = 1.2;
    private static final double WILL_MULT = 2.0;
    private static final double SPI_MULT = 2.2;
    private static final double MND_MULT = 1.0;
    
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
        return "Espiritualista";
    }
    
    @Override
    public String getStyleDescription() {
        return "Focado em Ki e ataques mÃ¡gicos. Excelente em ofensiva mÃ¡gica mas fraco em combate corpo a corpo.";
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
        return "Spiritualist{" +
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
