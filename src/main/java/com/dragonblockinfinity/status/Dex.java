package com.dragonblockinfinity.status;

/**
 * Classe para gerenciar Destreza (Dex)
 * Dex se relaciona com Str do inimigo para esquiva, reduÃ§Ã£o de dano e knockback
 * Quanto maior Dex em relaÃ§Ã£o a Str do inimigo, mais imune fica
 */
public class Dex {
    private int dexterity;
    
    public Dex(int dexterity) {
        this.dexterity = dexterity;
    }
    
    // ===== GETTERS =====
    public int getDexterity() {
        return dexterity;
    }
    
    // ===== SETTERS =====
    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }
    
    public void addDexterity(int value) {
        this.dexterity += value;
    }
    
    // ===== CÃLCULOS DE RELAÃÃO DEX vs STR =====
    /**
     * Calcula o multiplicador de Dex em relaÃ§Ã£o a Str do inimigo
     * FÃ³rmula: Dex / Str do inimigo
     */
    public double getDexMultiplier(int enemyStrength) {
        if (enemyStrength <= 0) return 999; // Sem inimigo, defesa infinita
        return (double) dexterity / enemyStrength;
    }
    
    /**
     * Retorna uma descriÃ§Ã£o do nÃ­vel de vantagem
     */
    public String getDexAdvantageLevel(int enemyStrength) {
        double multiplier = getDexMultiplier(enemyStrength);
        
        if (multiplier >= 4.0) return "DOMINANTE (4x+)";
        if (multiplier >= 3.0) return "SUPERIOR (3x+)";
        if (multiplier >= 2.0) return "VANTAGEM (2x+)";
        if (multiplier >= 1.5) return "LEVE VANTAGEM (1.5x+)";
        return "NORMAL";
    }
    
    // ===== CHANCE DE ESQUIVA =====
    /**
     * Calcula chance de esquiva baseado na relaÃ§Ã£o Dex vs Str do inimigo
     * 
     * Multiplicador < 1.5x: 0% de esquiva
     * Multiplicador 1.5x: 25% de esquiva
     * Multiplicador 2x: 50% de esquiva
     * Multiplicador 3x: 80% de esquiva
     * Multiplicador 4x+: 95% de esquiva
     */
    public double getDodgeChance(int enemyStrength) {
        double multiplier = getDexMultiplier(enemyStrength);
        
        if (multiplier < 1.5) {
            return 0.0;
        } else if (multiplier < 2.0) {
            // Entre 1.5x e 2x: aumenta de 25% para 50%
            return 0.25 + (multiplier - 1.5) * 0.5;
        } else if (multiplier < 3.0) {
            // Entre 2x e 3x: aumenta de 50% para 80%
            return 0.50 + (multiplier - 2.0) * 0.3;
        } else if (multiplier < 4.0) {
            // Entre 3x e 4x: aumenta de 80% para 95%
            return 0.80 + (multiplier - 3.0) * 0.15;
        } else {
            // 4x ou mais: 95%+
            return 0.95;
        }
    }
    
    // ===== REDUÃÃO DE DANO =====
    /**
     * Calcula reduÃ§Ã£o de dano baseado na relaÃ§Ã£o Dex vs Str
     * 
     * Multiplicador < 2x: 0% de reduÃ§Ã£o
     * Multiplicador 2x: 20% de reduÃ§Ã£o
     * Multiplicador 3x: 70% de reduÃ§Ã£o
     * Multiplicador 4x+: 90% de reduÃ§Ã£o
     */
    public double getDamageReduction(int enemyStrength) {
        double multiplier = getDexMultiplier(enemyStrength);
        
        if (multiplier < 2.0) {
            return 0.0;
        } else if (multiplier < 3.0) {
            // Entre 2x e 3x: aumenta de 20% para 70%
            return 0.20 + (multiplier - 2.0) * 0.5;
        } else if (multiplier < 4.0) {
            // Entre 3x e 4x: aumenta de 70% para 90%
            return 0.70 + (multiplier - 3.0) * 0.2;
        } else {
            // 4x ou mais: 90%+
            return 0.90;
        }
    }
    
    /**
     * Calcula o dano final apÃ³s reduÃ§Ã£o por Dex
     */
    public int calculateFinalDamage(int incomingDamage, int enemyStrength) {
        double reduction = getDamageReduction(enemyStrength);
        int reducedDamage = (int)(incomingDamage * (1.0 - reduction));
        return Math.max(0, reducedDamage); // MÃ­nimo 0
    }
    
    // ===== KNOCKBACK =====
    /**
     * Calcula knockback recebido baseado em Dex
     * 
     * Multiplicador < 2x: knockback normal (1.0x)
     * Multiplicador 2x: knockback reduzido (0.5x)
     * Multiplicador 3x: knockback mÃ­nimo (0.1x)
     * Multiplicador 4x+: sem knockback (0.0x)
     */
    public double getKnockbackMultiplier(int enemyStrength) {
        double multiplier = getDexMultiplier(enemyStrength);
        
        if (multiplier < 2.0) {
            return 1.0;
        } else if (multiplier < 3.0) {
            // Entre 2x e 3x: reduz de 1.0 para 0.1
            return 1.0 - (multiplier - 2.0) * 0.9;
        } else if (multiplier < 4.0) {
            // Entre 3x e 4x: reduz de 0.1 para 0.0
            return 0.1 - (multiplier - 3.0) * 0.1;
        } else {
            // 4x ou mais: sem knockback
            return 0.0;
        }
    }
    
    // ===== CHANCE DE ACERTAR (REVERSO) =====
    /**
     * Quando DEX Ã© muito maior que STR, o inimigo tem dificuldade de acertar
     * 
     * Multiplicador < 3x: o inimigo consegue atacar normal
     * Multiplicador 3x: inimigo tem 50% de chance de acertar
     * Multiplicador 4x+: inimigo sÃ³ consegue atacar 5% das vezes (fica parado)
     */
    public double getEnemyAccuracyReduction(int enemyStrength) {
        double multiplier = getDexMultiplier(enemyStrength);
        
        if (multiplier < 3.0) {
            return 1.0; // Inimigo consegue atacar 100%
        } else if (multiplier < 4.0) {
            // Entre 3x e 4x: reduz de 100% para 50%
            return 1.0 - (multiplier - 3.0) * 0.5;
        } else {
            // 4x ou mais: inimigo tem sÃ³ 5% de chance de acertar
            return 0.05;
        }
    }
    
    // ===== ROLAGEM DE ESQUIVA =====
    /**
     * Testa se conseguiu esquivar
     */
    public boolean rollDodge(int enemyStrength) {
        return Math.random() < getDodgeChance(enemyStrength);
    }
    
    /**
     * Verifica se Ã© DOMINANTE (4x+) - praticamente fica parado
     */
    public boolean isDominant(int enemyStrength) {
        return getDexMultiplier(enemyStrength) >= 4.0;
    }
    
    /**
     * Verifica se tem VANTAGEM SUPERIOR (3x+)
     */
    public boolean isSuperior(int enemyStrength) {
        return getDexMultiplier(enemyStrength) >= 3.0;
    }
    
    /**
     * Verifica se tem VANTAGEM (2x+)
     */
    public boolean hasAdvantage(int enemyStrength) {
        return getDexMultiplier(enemyStrength) >= 2.0;
    }
    
    /**
     * Verifica se tem LEVE VANTAGEM (1.5x+)
     */
    public boolean hasLightAdvantage(int enemyStrength) {
        return getDexMultiplier(enemyStrength) >= 1.5;
    }
    
    @Override
    public String toString() {
        return "Dex{" +
                "Destreza=" + dexterity +
                '}';
    }
    
    /**
     * MÃ©todo para mostrar comparaÃ§Ã£o com inimigo
     */
    public String compareWithEnemy(int enemyStrength) {
        double multiplier = getDexMultiplier(enemyStrength);
        return "=== ANÃLISE DE COMBATE ===\n" +
                "Sua Destreza: " + dexterity + "\n" +
                "ForÃ§a do Inimigo: " + enemyStrength + "\n" +
                "Multiplicador: " + String.format("%.2f", multiplier) + "x\n" +
                "NÃ­vel: " + getDexAdvantageLevel(enemyStrength) + "\n" +
                "\nEsquiva: " + String.format("%.1f", getDodgeChance(enemyStrength) * 100) + "%\n" +
                "ReduÃ§Ã£o de Dano: " + String.format("%.1f", getDamageReduction(enemyStrength) * 100) + "%\n" +
                "Knockback: " + String.format("%.1f", getKnockbackMultiplier(enemyStrength) * 100) + "%\n" +
                "AcurÃ¡cia do Inimigo: " + String.format("%.1f", getEnemyAccuracyReduction(enemyStrength) * 100) + "%\n";
    }
}
