package com.dragonblockinfinity.status;

/**
 * Classe para gerenciar o atributo Força (Str)
 * Str consome Stamina para fazer dano
 * Quanto maior Str em relação a Con, maior o dano mas maior o consumo
 */
public class Str {
    private int strength;      // Força - aumenta dano
    private int constitution;  // Constituição - limita consumo de Stamina
    private int currentStamina;    // Stamina atual
    private int maxStamina;    // Stamina máxima
    
    // ===== HABILIDADE SECRETA: MODO BERSERK =====
    private int berserkCharge;     // Acúmulo de poder (0-100)
    private int maxBerserkCharge;  // Máximo de acúmulo (100)
    private boolean isBerserk;     // Está em modo berserk?
    private double berserkDamageMultiplier;  // Multiplicador de dano em berserk
    
    public Str(int constitution) {
        this.strength = 10;
        this.constitution = constitution;
        this.maxStamina = constitution * 5; // Stamina máxima = Con * 5
        this.currentStamina = maxStamina;
        
        // Habilidade Secreta
        this.berserkCharge = 0;
        this.maxBerserkCharge = 100;
        this.isBerserk = false;
        this.berserkDamageMultiplier = 1.0;
    }
    
    // ===== GETTERS =====
    public int getStrength() {
        return strength;
    }
    
    public int getConstitution() {
        return constitution;
    }
    
    public int getCurrentStamina() {
        return currentStamina;
    }
    
    public int getMaxStamina() {
        return maxStamina;
    }
    
    // ===== SETTERS =====
    public void setStrength(int strength) {
        this.strength = strength;
    }
    
    public void setConstitution(int constitution) {
        this.constitution = constitution;
        this.maxStamina = constitution * 5;
    }
    
    public void setCurrentStamina(int stamina) {
        this.currentStamina = Math.max(0, Math.min(stamina, maxStamina));
    }
    
    // ===== ADIÇÕES =====
    public void addStrength(int value) {
        this.strength += value;
    }
    
    public void addStamina(int value) {
        this.currentStamina = Math.min(currentStamina + value, maxStamina);
    }
    
    public void removeStamina(int value) {
        this.currentStamina = Math.max(0, currentStamina - value);
    }
    
    // ===== CÁLCULOS DE DANO =====
    /**
     * Calcula dano padrão quando Stamina = 0
     * Fórmula: (Força * 0.5) + 2
     */
    public int getDefaultDamage() {
        return (int)(strength * 0.5) + 2;
    }
    
    /**
     * Calcula o dano base da força quando tem stamina
     * Fórmula: (Força * 1.5) + 5
     */
    public int getBaseDamage() {
        if (currentStamina == 0) {
            return getDefaultDamage();
        }
        return (int)(strength * 1.5) + 5;
    }
    
    /**
     * Calcula consumo de Stamina baseado na diferença Str - Con
     * Fórmula: 5 + (Str - Con) * 0.5
     * Mínimo de 3 de consumo
     */
    public int getStaminaCost() {
        int difference = strength - constitution;
        int cost = 5 + (int)(difference * 0.5);
        return Math.max(3, cost);
    }
    
    /**
     * Calcula dano com a quantidade de stamina gasta
     * Quanto mais stamina consumir, mais dano faz
     */
    public int calculateDamageWithStamina(int staminaCost) {
        int baseDamage = getBaseDamage();
        
        // Se não tem stamina suficiente, faz dano padrão
        if (currentStamina < staminaCost) {
            return getDefaultDamage();
        }
        
        // Quanto maior o custo de stamina, maior o multiplicador de dano
        double staminaMultiplier = 1.0 + (staminaCost * 0.1);
        return (int)(baseDamage * staminaMultiplier);
    }
    
    /**
     * Executa um ataque consumindo stamina
     * Retorna o dano feito, ou dano padrão se não tiver stamina
     */
    public int executeAttack() {
        int staminaCost = getStaminaCost();
        int damage = calculateDamageWithStamina(staminaCost);
        
        // Consome stamina
        if (currentStamina >= staminaCost) {
            removeStamina(staminaCost);
        }
        
        return damage;
    }
    
    /**
     * Verifica se tem stamina suficiente para atacar normalmente
     */
    public boolean hasEnoughStamina() {
        return currentStamina >= getStaminaCost();
    }
    
    @Override
    public String toString() {
        return "Str{" +
                "Força=" + strength +
                ", Constituição=" + constitution +
                ", Stamina=" + currentStamina + "/" + maxStamina +
                ", DanoBase=" + getBaseDamage() +
                ", DanoPadrão=" + getDefaultDamage() +
                ", CustoStamina=" + getStaminaCost() +
                ", BerserkCharge=" + berserkCharge + "/" + maxBerserkCharge +
                ", Berserk=" + (isBerserk ? "ATIVO" : "Inativo") +
                '}';
    }
    
    // ===== HABILIDADE SECRETA: MODO BERSERK =====
    /**
     * HABILIDADE SECRETA - MODO BERSERK
     * 
     * Quanto mais dano o jogador leva, mais energia ele acumula (Berserk Charge)
     * Quando atinge 100 de carga, pode ativar um ataque devastador
     * Cada ponto de Berserk aumenta 1% de dano
     * 
     * Em Berserk:
     * - Dano aumenta em até 100% (2x)
     * - Consumo de Stamina é reduzido em 50%
     * - Tempo limitado (precisa gastar a carga)
     */
    
    public int getBerserkCharge() {
        return berserkCharge;
    }
    
    public boolean isBerserkMode() {
        return isBerserk;
    }
    
    public double getBerserkDamageMultiplier() {
        return berserkDamageMultiplier;
    }
    
    /**
     * Quando o jogador leva dano, ganha Berserk Charge
     * Quanto maior o dano levado, mais charge ganha
     */
    public void accumulateBerserkCharge(int damageReceived) {
        if (isBerserk) return; // Não acumula em berserk
        
        // Acumula 2 pontos de charge por cada ponto de dano levado
        int chargeGain = Math.min(damageReceived * 2, maxBerserkCharge - berserkCharge);
        berserkCharge += chargeGain;
        
        // Se atingiu 100, entra automaticamente em berserk
        if (berserkCharge >= maxBerserkCharge) {
            activateBerserk();
        }
    }
    
    /**
     * Ativa o modo Berserk
     * Aumenta dano baseado na carga acumulada
     */
    private void activateBerserk() {
        isBerserk = true;
        // Multiplicador varia de 1.0 (0 charge) até 2.0 (100 charge)
        berserkDamageMultiplier = 1.0 + (berserkCharge / (double) maxBerserkCharge);
    }
    
    /**
     * Calcula dano com multiplicador de Berserk
     */
    public int calculateBerserkDamage(int baseDamage) {
        return (int)(baseDamage * berserkDamageMultiplier);
    }
    
    /**
     * Calcula consumo de Stamina reduzido em Berserk (50% menos)
     */
    public int getBerserkStaminaCost(int normalCost) {
        if (!isBerserk) return normalCost;
        return (int)(normalCost * 0.5);
    }
    
    /**
     * Executa ataque em modo Berserk e consome a carga
     * Quanto mais carga tinha, mais dano faz
     */
    public int executeBerserkAttack() {
        if (!isBerserk || berserkCharge <= 0) {
            return executeAttack(); // Ataque normal se não está em berserk
        }
        
        int baseDamage = getBaseDamage();
        int berserkDamage = calculateBerserkDamage(baseDamage);
        
        // Consome menos stamina em Berserk
        int staminaCost = getBerserkStaminaCost(getStaminaCost());
        if (currentStamina >= staminaCost) {
            removeStamina(staminaCost);
        }
        
        // Consome toda a carga de Berserk
        int damageBonus = (int)(strength * (berserkCharge / 10.0)); // Bônus baseado na carga
        berserkCharge = 0;
        isBerserk = false;
        berserkDamageMultiplier = 1.0;
        
        return berserkDamage + damageBonus;
    }
    
    /**
     * Cancelar modo Berserk manualmente (perde a carga)
     */
    public void cancelBerserk() {
        isBerserk = false;
        berserkCharge = 0;
        berserkDamageMultiplier = 1.0;
    }
    
    /**
     * Verifica se pode ativar Berserk (está em 100% de carga)
     */
    public boolean canActivateBerserk() {
        return berserkCharge >= maxBerserkCharge;
    }
    
    /**
     * Mostra informações do Berserk
     */
    public String getBerserkInfo() {
        return "=== MODO BERSERK (HABILIDADE SECRETA) ===\n" +
                "Carga Acumulada: " + berserkCharge + "/" + maxBerserkCharge + "\n" +
                "Status: " + (isBerserk ? "ATIVO - Multiplicador: " + String.format("%.1f", berserkDamageMultiplier) + "x" : "Inativo") + "\n" +
                "Dano Adicional: " + (int)(strength * (berserkCharge / 10.0)) + "\n" +
                "Consumo de Stamina Reduzido: 50% em Berserk\n" +
                "Dica: Leve dano para acumular carga e ativar o Berserk!";
    }
}
