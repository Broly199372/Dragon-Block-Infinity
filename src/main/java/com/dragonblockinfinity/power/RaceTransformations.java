package com.dragonblockinfinity.power;

import com.dragonblockinfinity.race.RaceEnum;

/**
 * Sistema de transformações especiais por raça
 */
public class RaceTransformations {
    
    /**
     * Super Saiyan - transf. clássica de Saiyajin
     */
    public static class SuperSaiyan {
        public static final double STRENGTH_MULTIPLIER = 2.0;
        public static final double KI_MULTIPLIER = 2.2;
        public static final double SPEED_MULTIPLIER = 1.8;
        public static final double KI_CONSUMPTION = 2.0; // Consome 2x mais Ki
        
        public static boolean canTransform(int kiPercent) {
            return kiPercent >= 50; // Precisa de 50% de Ki
        }
    }
    
    /**
     * Golden Form - forma final de Saiyajin
     */
    public static class GoldenForm {
        public static final double STRENGTH_MULTIPLIER = 3.0;
        public static final double KI_MULTIPLIER = 4.0;
        public static final double SPEED_MULTIPLIER = 3.0;
        public static final double KI_CONSUMPTION = 4.0; // Consome 4x mais Ki
        
        public static boolean canTransform(int kiPercent) {
            return kiPercent >= 75; // Precisa de 75% de Ki
        }
    }
    
    /**
     * Arcosian Final Form - forma final do Arcosian
     */
    public static class ArcosianFinalForm {
        public static final double STRENGTH_MULTIPLIER = 2.5;
        public static final double KI_MULTIPLIER = 3.0;
        public static final double SPEED_MULTIPLIER = 2.2;
        public static final double KI_CONSUMPTION = 3.0;
        
        public static boolean canTransform(int kiPercent) {
            return kiPercent >= 60;
        }
    }
    
    /**
     * Half-Saiyan Hybrid Mode - combinação de poderes
     */
    public static class HybridMode {
        public static final double STRENGTH_MULTIPLIER = 1.6;
        public static final double KI_MULTIPLIER = 1.8;
        public static final double SPEED_MULTIPLIER = 1.7;
        public static final double KI_CONSUMPTION = 1.5;
        
        public static boolean canTransform(int kiPercent) {
            return kiPercent >= 40;
        }
    }
    
    /**
     * Human Zenkai Boost - Humano fica mais forte após se recuperar de quase morte
     */
    public static class ZenkaiBoost {
        public static final double STRENGTH_MULTIPLIER = 1.3;
        public static final double KI_MULTIPLIER = 1.4;
        public static final double SPEED_MULTIPLIER = 1.2;
        public static final double KI_CONSUMPTION = 1.0; // Sem custo adicional
        
        public static boolean canActivate(int healthPercent, boolean wasNearDeath) {
            return wasNearDeath && healthPercent >= 50;
        }
    }
    
    /**
     * Obtém o multiplicador de transformação baseado na raça
     */
    public static double getTransformationMultiplier(RaceEnum race, String transformName) {
        return switch (race) {
            case SAIYAN -> switch (transformName) {
                case "super_saiyan" -> SuperSaiyan.STRENGTH_MULTIPLIER;
                case "golden_form" -> GoldenForm.STRENGTH_MULTIPLIER;
                default -> 1.0;
            };
            case ARCONSIAN -> switch (transformName) {
                case "final_form" -> ArcosianFinalForm.STRENGTH_MULTIPLIER;
                default -> 1.0;
            };
            case HALF -> switch (transformName) {
                case "hybrid_mode" -> HybridMode.STRENGTH_MULTIPLIER;
                default -> 1.0;
            };
            case HUMAN -> switch (transformName) {
                case "zenkai_boost" -> ZenkaiBoost.STRENGTH_MULTIPLIER;
                default -> 1.0;
            };
            default -> 1.0;
        };
    }
}
