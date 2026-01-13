package com.dragonblockinfinity.race;

import com.dragonblockinfinity.fightstyle.FightStyleEnum;
import com.dragonblockinfinity.status.*;

/**
 * Raça Half-Saiyan (metade-saiyajin)
 * Híbrido entre Saiyajim e Humano com potencial extraordinário
 * Força: Ki equilibrado, stats balanceados
 */
public class HalfSaiyan implements IRace {
    
    @Override
    public int getMaxKiBase() {
        // Entre Human (3000) e Saiyan (5000)
        return 4000;
    }
    
    @Override
    public double getKiMultiplier() {
        // Multiplicador intermediário
        return 1.4;
    }
    
    @Override
    public FightStyleEnum getPreferredFightStyle() {
        // Half-Saiyan combina bem com Martial Arts
        return FightStyleEnum.MARTIAL_ARTS;
    }
    
    @Override
    public int getBaseStrength() {
        // Força intermediária
        return 12;
    }
    
    @Override
    public int getBaseConstitution() {
        // Resistência equilibrada
        return 11;
    }
    
    @Override
    public int getBaseDexterity() {
        // Boa destreza, herança humana
        return 11;
    }
    
    @Override
    public int getBaseWillpower() {
        // Ki balance normal
        return 10;
    }
    
    @Override
    public int getBaseSpirit() {
        // Espírito moderado
        return 11;
    }
    
    @Override
    public int getBaseMind() {
        // Mente equilibrada
        return 10;
    }
    
    @Override
    public String getRaceName() {
        return "Half-Saiyajin";
    }
    
    @Override
    public String getRaceDescription() {
        return "Híbrido com potencial extraordinário";
    }
    
    @Override
    public int calculateMaxKi() {
        return (int) (getMaxKiBase() * getKiMultiplier());
    }
}
