package com.dragonblockinfinity.power;

import com.dragonblockinfinity.race.RaceEnum;

/**
 * Sistema de habilidades especiais de Ki por raÃ§a
 */
public class RaceKiAbilities {
    
    /**
     * Calcula o regeneraÃ§Ã£o de Ki por tick baseado na raÃ§a
     */
    public static double getKiRegenPerTick(RaceEnum race) {
        return switch (race) {
            case SAIYAN -> 1.2; // Saiyajin regenera 20% mais rÃ¡pido
            case HALF -> 1.1; // Half-Saiyan regenera 10% mais rÃ¡pido
            case HUMAN -> 1.0; // Humano regeneraÃ§Ã£o padrÃ£o
            case ARCONSIAN -> 0.95; // Arcosian regenera um pouco mais lento
            default -> 1.0;
        };
    }
    
    /**
     * Multiplica dano de Ki baseado na raÃ§a
     */
    public static double getKiDamageMultiplier(RaceEnum race) {
        return switch (race) {
            case SAIYAN -> 1.15; // Saiyajin causa 15% mais dano
            case HALF -> 1.05; // Half-Saiyan causa 5% mais dano
            case HUMAN -> 1.0; // Humano padrÃ£o
            case ARCONSIAN -> 1.25; // Arcosian causa 25% mais dano de Ki
            default -> 1.0;
        };
    }
    
    /**
     * Habilidade especial: Saiyan - pode aumentar forÃ§a ao receber dano
     */
    public static double getSaiyanPowerUp(int damageReceived) {
        // Saiyajin fica mais forte quando recebe dano
        // A cada 100 de dano recebido, ganha +1% de forÃ§a
        return 1.0 + (damageReceived / 100.0) * 0.01;
    }
    
    /**
     * Habilidade especial: Saiyan - Zenkai Boost apÃ³s recuperaÃ§Ã£o
     * Ganha 2x de aumento em stats (Str, Dex, Con) ao se recuperar de dano extremo
     */
    public static double getSaiyanZenkai(int healthPercentBefore) {
        // Zenkai ativa quando se recupera de vida muito baixa
        // Quanto mais perto da morte estava, mais forte fica
        // MÃ¡ximo 2.0x multiplicador
        if (healthPercentBefore <= 10) {
            return 2.0; // 2x multiplicador para Str, Dex, Con
        } else if (healthPercentBefore <= 30) {
            return 1.5; // 1.5x se estava moderadamente machucado
        }
        return 1.0; // Sem bonus se nÃ£o estava perto da morte
    }
    /**
     * Habilidade especial: Arcosian - forma final aumenta tudo
     */
    public static double getArcosianFormMultiplier(boolean finalForm) {
        return finalForm ? 1.5 : 1.0; // Forma final multiplica tudo por 1.5x
    }
    
    /**
     * Habilidade especial: Human - Ki se recupera mais rÃ¡pido em combate
     */
    public static double getHumanCombatRegenBonus(boolean inCombat) {
        return inCombat ? 1.3 : 1.0; // 30% mais rÃ¡pido durante combate
    }
    
    /**
     * Habilidade especial: Half-Saiyan - combinaÃ§Ã£o de habilidades
     */
    public static double getHalfSaiyanAdaptability(int combatDuration) {
        // Fica mais forte conforme o tempo em combate passa
        // A cada 20 segundos, ganha +0.5% de adaptaÃ§Ã£o
        return 1.0 + (combatDuration / 400.0) * 0.005;
    }
    
    /**
     * Habilidade especial: Half-Saiyan - Zenkai Boost (versÃ£o reduzida)
     * Ganha 1.5x de aumento em stats (Str, Dex, Con) ao se recuperar de dano extremo
     */
    public static double getHalfSaiyanZenkai(int healthPercentBefore) {
        // Zenkai ativa quando se recupera de vida muito baixa
        // VersÃ£o reduzida em comparaÃ§Ã£o ao Saiyajin puro (1.5x vs 2.0x)
        if (healthPercentBefore <= 10) {
            return 1.5; // 1.5x multiplicador para Str, Dex, Con
        } else if (healthPercentBefore <= 30) {
            return 1.25; // 1.25x se estava moderadamente machucado
        }
        return 1.0; // Sem bonus se nÃ£o estava perto da morte
    }
}
