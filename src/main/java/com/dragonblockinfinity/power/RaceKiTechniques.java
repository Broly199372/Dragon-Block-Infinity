package com.dragonblockinfinity.power;

import com.dragonblockinfinity.race.RaceEnum;

/**
 * Sistema de tÃ©cnicas especiais de Ki por raÃ§a
 * Cada raÃ§a tem suas prÃ³prias tÃ©cnicas Ãºnicas
 */
public class RaceKiTechniques {
    
    /**
     * TÃ©cnicas de Saiyajin
     */
    public static class SaiyanTechniques {
        public static final String KAIOKEN = "kaioken"; // Aumenta forÃ§a mas consome stamina
        public static final String SPIRIT_BOMB = "spirit_bomb"; // TÃ©cnica poderosa coletiva
        public static final String KAMEHAMEHA = "kamehameha"; // Raio de Ki poderoso
        
        public static int getKiCost(String technique) {
            return switch (technique) {
                case KAIOKEN -> 15; // 15% de Ki
                case SPIRIT_BOMB -> 60; // 60% de Ki (muito caro)
                case KAMEHAMEHA -> 40; // 40% de Ki
                default -> 0;
            };
        }
        
        public static double getDamage(String technique, int kiLevel) {
            return switch (technique) {
                case KAIOKEN -> kiLevel * 1.5;
                case SPIRIT_BOMB -> kiLevel * 3.0;
                case KAMEHAMEHA -> kiLevel * 2.5;
                default -> 0;
            };
        }
    }
    
    /**
     * TÃ©cnicas de Arcosian
     */
    public static class ArcosianTechniques {
        public static final String DEATH_BEAM = "death_beam"; // Raio mortal precisso
        public static final String FINAL_FLASH = "final_flash"; // ExplosÃ£o final poderosa
        public static final String NOVA_STRIKE = "nova_strike"; // Ataque explosivo
        
        public static int getKiCost(String technique) {
            return switch (technique) {
                case DEATH_BEAM -> 25; // 25% de Ki
                case FINAL_FLASH -> 70; // 70% de Ki (muito caro)
                case NOVA_STRIKE -> 50; // 50% de Ki
                default -> 0;
            };
        }
        
        public static double getDamage(String technique, int kiLevel) {
            return switch (technique) {
                case DEATH_BEAM -> kiLevel * 2.0; // Muito preciso e letal
                case FINAL_FLASH -> kiLevel * 3.2;
                case NOVA_STRIKE -> kiLevel * 2.8;
                default -> 0;
            };
        }
    }
    
    /**
     * TÃ©cnicas de Half-Saiyan
     */
    public static class HalfSaiyanTechniques {
        public static final String HYBRID_BLAST = "hybrid_blast"; // CombinaÃ§Ã£o de tÃ©cnicas
        public static final String POWER_SURGE = "power_surge"; // Aumento rÃ¡pido de forÃ§a
        public static final String ADAPTIVE_COUNTER = "adaptive_counter"; // Contra-ataque adaptÃ¡vel
        
        public static int getKiCost(String technique) {
            return switch (technique) {
                case HYBRID_BLAST -> 35; // 35% de Ki
                case POWER_SURGE -> 30; // 30% de Ki
                case ADAPTIVE_COUNTER -> 20; // 20% de Ki (barato)
                default -> 0;
            };
        }
        
        public static double getDamage(String technique, int kiLevel) {
            return switch (technique) {
                case HYBRID_BLAST -> kiLevel * 2.2;
                case POWER_SURGE -> kiLevel * 1.8; // Mais sobre aumento que dano imediato
                case ADAPTIVE_COUNTER -> kiLevel * 1.5;
                default -> 0;
            };
        }
    }
    
    /**
     * TÃ©cnicas de Humano
     */
    public static class HumanTechniques {
        public static final String CHARGED_SHOT = "charged_shot"; // Tiro carregado
        public static final String ENERGY_WAVE = "energy_wave"; // Onda de energia controlada
        public static final String MULTI_ATTACK = "multi_attack"; // MÃºltiplos ataques rÃ¡pidos
        
        public static int getKiCost(String technique) {
            return switch (technique) {
                case CHARGED_SHOT -> 40; // 40% de Ki
                case ENERGY_WAVE -> 45; // 45% de Ki
                case MULTI_ATTACK -> 35; // 35% de Ki
                default -> 0;
            };
        }
        
        public static double getDamage(String technique, int kiLevel) {
            return switch (technique) {
                case CHARGED_SHOT -> kiLevel * 2.0;
                case ENERGY_WAVE -> kiLevel * 2.1;
                case MULTI_ATTACK -> kiLevel * 1.9;
                default -> 0;
            };
        }
    }
    
    /**
     * ObtÃ©m custo de Ki de uma tÃ©cnica baseado na raÃ§a
     */
    public static int getTechniqueCost(RaceEnum race, String technique) {
        return switch (race) {
            case SAIYAN -> SaiyanTechniques.getKiCost(technique);
            case ARCONSIAN -> ArcosianTechniques.getKiCost(technique);
            case HALF -> HalfSaiyanTechniques.getKiCost(technique);
            case HUMAN -> HumanTechniques.getKiCost(technique);
            default -> 0;
        };
    }
    
    /**
     * ObtÃ©m dano de uma tÃ©cnica baseado na raÃ§a e nÃ­vel de Ki
     */
    public static double getTechniqueDamage(RaceEnum race, String technique, int kiLevel) {
        return switch (race) {
            case SAIYAN -> SaiyanTechniques.getDamage(technique, kiLevel);
            case ARCONSIAN -> ArcosianTechniques.getDamage(technique, kiLevel);
            case HALF -> HalfSaiyanTechniques.getDamage(technique, kiLevel);
            case HUMAN -> HumanTechniques.getDamage(technique, kiLevel);
            default -> 0;
        };
    }
}
