package com.dragonblockinfinity.race;

import com.dragonblockinfinity.fightstyle.FightStyleEnum;
import com.dragonblockinfinity.status.*;

/**
 * RaÃ§a Half-Saiyan (metade-saiyajin)
 * HÃ­brido entre Saiyajim e Humano com potencial extraordinÃ¡rio
 * ForÃ§a: Ki equilibrado, stats balanceados
 */
public class HalfSaiyan implements IRace {
    
    @Override
    public int getMaxKiBase() {
        // Entre Human (3000) e Saiyan (5000)
        return 4000;
    }
    
    @Override
    public double getKiMultiplier() {
        // Multiplicador intermediÃ¡rio
        return 1.4;
    }
    
    @Override
    public FightStyleEnum getPreferredFightStyle() {
        // Half-Saiyan combina bem com Martial Arts
        return FightStyleEnum.MARTIAL_ARTS;
    }
    
    @Override
    public int getBaseStrength() {
        // ForÃ§a intermediÃ¡ria
        return 12;
    }
    
    @Override
    public int getBaseConstitution() {
        // ResistÃªncia equilibrada
        return 11;
    }
    
    @Override
    public int getBaseDexterity() {
        // Boa destreza, heranÃ§a humana
        return 11;
    }
    
    @Override
    public int getBaseWillpower() {
        // Ki balance normal
        return 10;
    }
    
    @Override
    public int getBaseSpirit() {
        // EspÃ­rito moderado
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
        return "HÃ­brido com potencial extraordinÃ¡rio";
    }
    
    @Override
    public int calculateMaxKi() {
        return (int) (getMaxKiBase() * getKiMultiplier());
    }
}
