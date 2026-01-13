package com.dragonblockinfinity.race;

import com.dragonblockinfinity.fightstyle.FightStyleEnum;
import com.dragonblockinfinity.status.*;

/**
 * Raça Arcosian
 * Tirano intergaláctico com poder lendário e ki colossal
 * Força: Ki muito alto, stats físicos elevados
 */
public class Arcosian implements IRace {
    
    @Override
    public int getMaxKiBase() {
        // Ki mais alto que Saiyajin
        return 6000;
    }
    
    @Override
    public double getKiMultiplier() {
        // Multiplicador de ki extremamente alto
        return 1.8;
    }
    
    @Override
    public FightStyleEnum getPreferredFightStyle() {
        // Arcosians are pure warriors
        return FightStyleEnum.WARRIOR;
    }
    
    @Override
    public int getBaseStrength() {
        // Força excepcional, rival de Saiyajin
        return 14;
    }
    
    @Override
    public int getBaseConstitution() {
        // Resistência comparável a Saiyajin
        return 13;
    }
    
    @Override
    public int getBaseDexterity() {
        // Destreza moderada, foco em poder bruto
        return 9;
    }
    
    @Override
    public int getBaseWillpower() {
        // Vontade comparável
        return 9;
    }
    
    @Override
    public int getBaseSpirit() {
        // Espírito intermediário
        return 9;
    }
    
    @Override
    public int getBaseMind() {
        // Mente moderada, tático
        return 10;
    }
    
    @Override
    public String getRaceName() {
        return "Arcosian";
    }
    
    @Override
    public String getRaceDescription() {
        return "Tirano intergaláctico com poder lendário";
    }
    
    @Override
    public int calculateMaxKi() {
        return (int) (getMaxKiBase() * getKiMultiplier());
    }
}
