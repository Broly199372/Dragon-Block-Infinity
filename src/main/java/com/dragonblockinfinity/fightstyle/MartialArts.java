package com.dragonblockinfinity.fightstyle;

/**
 * Estilo Artes Marciais
 * Equilibrado em todos os aspectos
 * 
 * Stats Base:
 * - Str: 11 (médio-alto)
 * - Con: 11 (médio-alto)
 * - Dex: 12 (médio-alto)
 * - Will: 11 (médio-alto)
 * - Spi: 11 (médio-alto)
 * - Mnd: 11 (médio-alto)
 * 
 * Multiplicadores:
 * - Str: 1.8x (bônus equilibrado)
 * - Con: 1.8x (bônus equilibrado)
 * - Dex: 1.8x (bônus equilibrado)
 * - Will: 1.2x (bônus pequeno)
 * - Spi: 1.2x (bônus pequeno)
 * - Mnd: 1.0x (neutro)
 */
public class MartialArts implements IFightStyle {
    
    // Stats base
    private static final int BASE_STR = 11;
    private static final int BASE_CON = 11;
    private static final int BASE_DEX = 12;
    private static final int BASE_WILL = 11;
    private static final int BASE_SPI = 11;
    private static final int BASE_MND = 11;
    
    // Multiplicadores
    private static final double STR_MULT = 1.8;
    private static final double CON_MULT = 1.8;
    private static final double DEX_MULT = 1.8;
    private static final double WILL_MULT = 1.2;
    private static final double SPI_MULT = 1.2;
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
        return "Artes Marciais";
    }
    
    @Override
    public String getStyleDescription() {
        return "Estilo equilibrado e versátil. Bom em vários aspectos mas não se destaca em nenhum.";
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
        return "MartialArts{" +
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
