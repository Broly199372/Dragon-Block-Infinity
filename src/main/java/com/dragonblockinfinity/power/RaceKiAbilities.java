package com.dragonblockinfinity.power;

import com.dragonblockinfinity.race.RaceEnum;

/**
 * Sistema de habilidades especiais de Ki por raça
 */
public class RaceKiAbilities {
    
    /**
     * Calcula o regeneração de Ki por tick baseado na raça
     */
    public static double getKiRegenPerTick(RaceEnum race) {
        return switch (race) {
            case SAIYAN -> 1.2; // Saiyajin regenera 20% mais rápido
            case HALF -> 1.1; // Half-Saiyan regenera 10% mais rápido
            case HUMAN -> 1.0; // Humano regeneração padrão
            case ARCONSIAN -> 0.95; // Arcosian regenera um pouco mais lento
            default -> 1.0;
        };
    }
    
    /**
     * Multiplica dano de Ki baseado na raça
     */
    public static double getKiDamageMultiplier(RaceEnum race) {
        return switch (race) {
            case SAIYAN -> 1.15; // Saiyajin causa 15% mais dano
            case HALF -> 1.05; // Half-Saiyan causa 5% mais dano
            case HUMAN -> 1.0; // Humano padrão
            case ARCONSIAN -> 1.25; // Arcosian causa 25% mais dano de Ki
            default -> 1.0;
        };
    }
    
    /**
     * Habilidade especial: Saiyan - pode aumentar força ao receber dano
     */
    public static double getSaiyanPowerUp(int damageReceived) {
        // Saiyajin fica mais forte quando recebe dano
        // A cada 100 de dano recebido, ganha +1% de força
        return 1.0 + (damageReceived / 100.0) * 0.01;
    }
    
    /**
     * Habilidade especial: Saiyan - Zenkai Boost após recuperação
     * Ganha 2x de aumento em stats (Str, Dex, Con) ao se recuperar de dano extremo
     */
    public static double getSaiyanZenkai(int healthPercentBefore) {
        // Zenkai ativa quando se recupera de vida muito baixa
        // Quanto mais perto da morte estava, mais forte fica
        // Máximo 2.0x multiplicador
        if (healthPercentBefore <= 10) {
            return 2.0; // 2x multiplicador para Str, Dex, Con
        } else if (healthPercentBefore <= 30) {
            return 1.5; // 1.5x se estava moderadamente machucado
        }
        return 1.0; // Sem bonus se não estava perto da morte
    }
    /**
     * Habilidade especial: Arcosian - forma final aumenta tudo
     */
    public static double getArcosianFormMultiplier(boolean finalForm) {
        return finalForm ? 1.5 : 1.0; // Forma final multiplica tudo por 1.5x
    }
    
    /**
     * Habilidade especial: Human - Ki se recupera mais rápido em combate
     */
    public static double getHumanCombatRegenBonus(boolean inCombat) {
        return inCombat ? 1.3 : 1.0; // 30% mais rápido durante combate
    }
    
    /**
     * Habilidade especial: Half-Saiyan - combinação de habilidades
     */
    public static double getHalfSaiyanAdaptability(int combatDuration) {
        // Fica mais forte conforme o tempo em combate passa
        // A cada 20 segundos, ganha +0.5% de adaptação
        return 1.0 + (combatDuration / 400.0) * 0.005;
    }
    
    /**
     * Habilidade especial: Half-Saiyan - Zenkai Boost (versão reduzida)
     * Ganha 1.5x de aumento em stats (Str, Dex, Con) ao se recuperar de dano extremo
     */
    public static double getHalfSaiyanZenkai(int healthPercentBefore) {
        // Zenkai ativa quando se recupera de vida muito baixa
        // Versão reduzida em comparação ao Saiyajin puro (1.5x vs 2.0x)
        if (healthPercentBefore <= 10) {
            return 1.5; // 1.5x multiplicador para Str, Dex, Con
        } else if (healthPercentBefore <= 30) {
            return 1.25; // 1.25x se estava moderadamente machucado
        }
        return 1.0; // Sem bonus se não estava perto da morte
    }
}
