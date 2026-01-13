package com.dragonblockinfinity.status;

/**
 * Classe para gerenciar Constituição (Con) com vida, stamina e regeneração
 */
public class Con {
    private int constitution;
    private int currentHealth;
    private int maxHealth;
    private int currentStamina;
    private int maxStamina;
    
    // Regeneração
    private double healthRegenRate;    // % da vida máxima por tick
    private double staminaRegenRate;   // % da stamina máxima por tick
    
    public Con(int constitution) {
        this.constitution = constitution;
        this.maxHealth = constitution * 5;
        this.currentHealth = maxHealth;
        this.maxStamina = constitution * 5;
        this.currentStamina = maxStamina;
        
        // Taxa de regen padrão
        this.healthRegenRate = 0.01;    // 1% da vida por tick
        this.staminaRegenRate = 0.03;   // 3% da stamina por tick
    }
    
    // ===== GETTERS =====
    public int getConstitution() {
        return constitution;
    }
    
    public int getCurrentHealth() {
        return currentHealth;
    }
    
    public int getMaxHealth() {
        return maxHealth;
    }
    
    public int getCurrentStamina() {
        return currentStamina;
    }
    
    public int getMaxStamina() {
        return maxStamina;
    }
    
    public double getHealthRegenRate() {
        return healthRegenRate;
    }
    
    public double getStaminaRegenRate() {
        return staminaRegenRate;
    }
    
    // ===== SETTERS =====
    public void setConstitution(int value) {
        this.constitution = value;
        this.maxHealth = constitution * 5;
        if (currentHealth > maxHealth) {
            currentHealth = maxHealth;
        }
        this.maxStamina = constitution * 5;
        if (currentStamina > maxStamina) {
            currentStamina = maxStamina;
        }
    }
    
    public void setHealthRegenRate(double rate) {
        this.healthRegenRate = Math.max(0, Math.min(rate, 1.0)); // Limita entre 0 e 1
    }
    
    public void setStaminaRegenRate(double rate) {
        this.staminaRegenRate = Math.max(0, Math.min(rate, 1.0)); // Limita entre 0 e 1
    }
    
    // ===== ADIÇÕES =====
    public void addConstitution(int value) {
        setConstitution(constitution + value);
    }
    
    // ===== VIDA =====
    public void heal(int amount) {
        currentHealth = Math.min(currentHealth + amount, maxHealth);
    }
    
    public void takeDamage(int amount) {
        currentHealth = Math.max(0, currentHealth - amount);
    }
    
    public int getHealthDeficit() {
        return maxHealth - currentHealth;
    }
    
    public double getHealthPercentage() {
        return (double) currentHealth / maxHealth;
    }
    
    // ===== STAMINA =====
    public void restoreStamina(int amount) {
        currentStamina = Math.min(currentStamina + amount, maxStamina);
    }
    
    public void consumeStamina(int amount) {
        currentStamina = Math.max(0, currentStamina - amount);
    }
    
    public int getStaminaDeficit() {
        return maxStamina - currentStamina;
    }
    
    public double getStaminaPercentage() {
        return (double) currentStamina / maxStamina;
    }
    
    // ===== REGENERAÇÃO =====
    /**
     * Regenera vida baseado na taxa de regeneração
     * Retorna a quantidade de vida regenerada
     */
    public int regenerateHealth() {
        int regenAmount = (int)(maxHealth * healthRegenRate);
        heal(regenAmount);
        return regenAmount;
    }
    
    /**
     * Regenera stamina baseado na taxa de regeneração
     * Retorna a quantidade de stamina regenerada
     */
    public int regenerateStamina() {
        int regenAmount = (int)(maxStamina * staminaRegenRate);
        restoreStamina(regenAmount);
        return regenAmount;
    }
    
    /**
     * Regenera vida E stamina ao mesmo tempo (tick de regen)
     */
    public void tickRegeneration() {
        regenerateHealth();
        regenerateStamina();
    }
    
    /**
     * Calcula quantidade de vida regenerada por segundo
     * Baseado na taxa e constituição
     */
    public int getHealthRegenPerSecond() {
        return (int)(maxHealth * healthRegenRate);
    }
    
    /**
     * Calcula quantidade de stamina regenerada por segundo
     */
    public int getStaminaRegenPerSecond() {
        return (int)(maxStamina * staminaRegenRate);
    }
    
    // ===== BÔNUS =====
    /**
     * Bônus de vida: Con * 5
     */
    public int getLifeBonus() {
        return constitution * 5;
    }
    
    /**
     * Bônus de stamina: Con * 5
     */
    public int getStaminaBonus() {
        return constitution * 5;
    }
    
    /**
     * Quanto maior a constituição, melhor a regen
     */
    public void boostRegenFromConstitution() {
        // Aumenta taxa de regen em 0.1% por ponto de Con
        double bonus = constitution * 0.001;
        this.healthRegenRate = Math.min(healthRegenRate + bonus, 0.2);  // Máx 20%
        this.staminaRegenRate = Math.min(staminaRegenRate + bonus, 0.3); // Máx 30%
    }
    
    @Override
    public String toString() {
        return "Con{" +
                "Constituição=" + constitution +
                ", Vida=" + currentHealth + "/" + maxHealth + 
                " (" + String.format("%.1f", getHealthPercentage() * 100) + "%)" +
                ", Stamina=" + currentStamina + "/" + maxStamina +
                " (" + String.format("%.1f", getStaminaPercentage() * 100) + "%)" +
                ", RegenVida=" + getHealthRegenPerSecond() + "/s" +
                ", RegenStamina=" + getStaminaRegenPerSecond() + "/s" +
                '}';
    }
}

class Stamina {
    private int stamina;
    
    public Stamina() {
        this.stamina = 0;
    }
    
    public int getStamina() {
        return stamina;
    }
    
    public void setStamina(int value) {
        this.stamina = value;
    }
    
    public void addStamina(int value) {
        this.stamina += value;
    }
    
    public int getStaminaBonus() {
        return stamina * 6;
    }
}
