package com.dragonblockinfinity.fightstyle;

/**
 * Interface base para todos os estilos de luta
 * Define os multiplicadores e stats base de cada estilo
 */
public interface IFightStyle {
    
    /**
     * Retorna o multiplicador de ForÃ§a (Str)
     */
    double getStrengthMultiplier();
    
    /**
     * Retorna o multiplicador de ConstituiÃ§Ã£o (Con)
     */
    double getConstitutionMultiplier();
    
    /**
     * Retorna o multiplicador de Destreza (Dex)
     */
    double getDexterityMultiplier();
    
    /**
     * Retorna o multiplicador de Vontade (Will)
     */
    double getWillMultiplier();
    
    /**
     * Retorna o multiplicador de EspÃ­rito (Spi)
     */
    double getSpiritMultiplier();
    
    /**
     * Retorna o multiplicador de Mente (Mnd)
     */
    double getMindMultiplier();
    
    /**
     * Retorna o stat base de ForÃ§a
     */
    int getBaseStrength();
    
    /**
     * Retorna o stat base de ConstituiÃ§Ã£o
     */
    int getBaseConstitution();
    
    /**
     * Retorna o stat base de Destreza
     */
    int getBaseDexterity();
    
    /**
     * Retorna o stat base de Vontade
     */
    int getBaseWillpower();
    
    /**
     * Retorna o stat base de EspÃ­rito
     */
    int getBaseSpirit();
    
    /**
     * Retorna o stat base de Mente
     */
    int getBaseMind();
    
    /**
     * Retorna o nome do estilo
     */
    String getStyleName();
    
    /**
     * Retorna a descriÃ§Ã£o do estilo
     */
    String getStyleDescription();
    
    /**
     * Calcula BP baseado neste estilo
     */
    int calculateBP(int str, int con, int dex, int will, int spi, int mnd);
}
