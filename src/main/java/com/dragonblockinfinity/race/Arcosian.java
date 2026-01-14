package com.dragonblockinfinity.race;

import com.dragonblockinfinity.fightstyle.FightStyleEnum;
import com.dragonblockinfinity.status.*;

/**
 * RaÃ§a Arcosian
 * Tirano intergalÃ¡ctico com poder lendÃ¡rio e ki colossal
 * ForÃ§a: Ki muito alto, stats fÃ­sicos elevados
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
        // ForÃ§a excepcional, rival de Saiyajin
        return 14;
    }
    
    @Override
    public int getBaseConstitution() {
        // ResistÃªncia comparÃ¡vel a Saiyajin
        return 13;
    }
    
    @Override
    public int getBaseDexterity() {
        // Destreza moderada, foco em poder bruto
        return 9;
    }
    
    @Override
    public int getBaseWillpower() {
        // Vontade comparÃ¡vel
        return 9;
    }
    
    @Override
    public int getBaseSpirit() {
        // EspÃ­rito intermediÃ¡rio
        return 9;
    }
    
    @Override
    public int getBaseMind() {
        // Mente moderada, tÃ¡tico
        return 10;
    }
    
    @Override
    public String getRaceName() {
        return "Arcosian";
    }
    
    @Override
    public String getRaceDescription() {
        return "Tirano intergalÃ¡ctico com poder lendÃ¡rio";
    }
    
    @Override
    public int calculateMaxKi() {
        return (int) (getMaxKiBase() * getKiMultiplier());
    }
}
