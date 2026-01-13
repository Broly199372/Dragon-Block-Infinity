package com.dragonblockinfinity.race;

import com.dragonblockinfinity.fightstyle.FightStyleEnum;

/**
 * Interface base para todas as raças
 */
public interface IRace {
    
    /**
     * Retorna o Ki máximo base da raça
     */
    int getMaxKiBase();
    
    /**
     * Retorna o multiplicador de Ki (afeta Ki máximo)
     */
    double getKiMultiplier();
    
    /**
     * Retorna o estilo de luta preferido da raça
     */
    FightStyleEnum getPreferredFightStyle();
    
    /**
     * Retorna stat base de Força
     */
    int getBaseStrength();
    
    /**
     * Retorna stat base de Constituição
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
     * Retorna stat base de Espírito
     */
    int getBaseSpirit();
    
    /**
     * Retorna stat base de Mente
     */
    int getBaseMind();
    
    /**
     * Retorna o nome da raça
     */
    String getRaceName();
    
    /**
     * Retorna a descrição da raça
     */
    String getRaceDescription();
    
    /**
     * Calcula Ki máximo total da raça
     */
    int calculateMaxKi();
}
