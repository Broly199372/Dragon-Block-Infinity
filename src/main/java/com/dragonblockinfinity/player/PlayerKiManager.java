package com.dragonblockinfinity.player;

/**
 * Gerenciador de Ki do Jogador
 * Ki sobe de 1 em 1% a cada tick
 */
public class PlayerKiManager {
    
    private int maxKi;
    private int currentKi;
    private double kiPercentage;
    
    private int regenTicks;
    private int regenTicksNeeded;
    
    public PlayerKiManager(int maxKi) {
        this.maxKi = maxKi;
        this.currentKi = maxKi;
        this.kiPercentage = 100.0;
        this.regenTicksNeeded = 20; // 1 segundo
        this.regenTicks = regenTicksNeeded;
    }
    
    // ===== GETTERS =====
    public int getMaxKi() {
        return maxKi;
    }
    
    public int getCurrentKi() {
        return currentKi;
    }
    
    public double getKiPercentage() {
        return kiPercentage;
    }
    
    public int getTicksUntilNextRegen() {
        return regenTicks;
    }
    
    public double getRegenProgress() {
        return ((double)(regenTicksNeeded - regenTicks) / regenTicksNeeded) * 100.0;
    }
    
    // ===== SETTERS =====
    public void setMaxKi(int maxKi) {
        this.maxKi = maxKi;
        if (currentKi > maxKi) {
            currentKi = maxKi;
            updatePercentage();
        }
    }
    
    public void setRegenSpeed(int ticksPerPercent) {
        this.regenTicksNeeded = Math.max(1, ticksPerPercent);
    }
    
    // ===== KI OPERATIONS =====
    public void consumeKi(int amount) {
        currentKi = Math.max(0, currentKi - amount);
        updatePercentage();
    }
    
    public void consumeKiPercent(double percent) {
        int amount = (int)(maxKi * (percent / 100.0));
        consumeKi(amount);
    }
    
    public void restoreKi(int amount) {
        currentKi = Math.min(currentKi + amount, maxKi);
        updatePercentage();
    }
    
    public void restoreKiPercent(double percent) {
        int amount = (int)(maxKi * (percent / 100.0));
        restoreKi(amount);
    }
    
    public boolean hasKi(int required) {
        return currentKi >= required;
    }
    
    public boolean hasKiPercent(double percent) {
        return kiPercentage >= percent;
    }
    
    // ===== REGENERAÇÃO =====
    /**
     * Ki regenera 1% a cada X ticks
     */
    public void tickRegeneration() {
        if (currentKi >= maxKi) {
            regenTicks = regenTicksNeeded;
            return;
        }
        
        regenTicks--;
        
        if (regenTicks <= 0) {
            // Sobe 1%
            int onePercent = Math.max(1, maxKi / 100);
            currentKi = Math.min(currentKi + onePercent, maxKi);
            updatePercentage();
            regenTicks = regenTicksNeeded;
        }
    }
    
    public void restoreFull() {
        currentKi = maxKi;
        kiPercentage = 100.0;
        regenTicks = regenTicksNeeded;
    }
    
    public void drain() {
        currentKi = 0;
        kiPercentage = 0.0;
    }
    
    public void setKiPercent(double percent) {
        percent = Math.max(0, Math.min(100, percent));
        currentKi = (int)(maxKi * (percent / 100.0));
        updatePercentage();
    }
    
    // ===== PRIVADO =====
    private void updatePercentage() {
        kiPercentage = maxKi <= 0 ? 0.0 : (double) currentKi / maxKi * 100.0;
    }
    
    @Override
    public String toString() {
        return "PlayerKi{" +
                currentKi + "/" + maxKi +
                " (" + String.format("%.1f", kiPercentage) + "%)" +
                "}";
    }
}
