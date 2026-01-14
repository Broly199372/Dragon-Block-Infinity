package com.dragonblockinfinity.race;

import com.dragonblockinfinity.fightstyle.FightStyleEnum;

/**
 * Interface base para todas as raÃ§as
 */
public interface IRace {
    
    /**
     * Retorna o Ki mÃ¡ximo base da raÃ§a
     */
    int getMaxKiBase();
    
    /**
     * Retorna o multiplicador de Ki (afeta Ki mÃ¡ximo)
     */
    double getKiMultiplier();
    
    /**
     * Retorna o estilo de luta preferido da raÃ§a
     */
    FightStyleEnum getPreferredFightStyle();
    
    /**
     * Retorna stat base de ForÃ§a
     */
    int getBaseStrength();
    
    /**
     * Retorna stat base de ConstituiÃ§Ã£o
     */
    int getBaseConstitution();
    
    /**
     * Retorna stat base de Destreza
     */
    int getBaseDexterity();
    
    /**
     * Retorna stat base de Vontade
     */
    int getBaseWillpower();
    
    /**
     * Retorna stat base de EspÃ­rito
     */
    int getBaseSpirit();
    
    /**
     * Retorna stat base de Mente
     */
    int getBaseMind();
    
    /**
     * Retorna o nome da raÃ§a
     */
    String getRaceName();
    
    /**
     * Retorna a descriÃ§Ã£o da raÃ§a
     */
    String getRaceDescription();
    
    /**
     * Calcula Ki mÃ¡ximo total da raÃ§a
     */
    int calculateMaxKi();
}
