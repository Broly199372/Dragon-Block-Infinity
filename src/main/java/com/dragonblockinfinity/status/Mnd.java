package com.dragonblockinfinity.status;

/**
 * Classe para gerenciar Mente (Mnd) - BÃSICO
 * Mnd controla inteligÃªncia, precisÃ£o e detecÃ§Ã£o
 */
public class Mnd {
    private int mind;  // Mente - inteligÃªncia
    
    public Mnd(int mind) {
        this.mind = mind;
    }
    
    // ===== GETTERS =====
    public int getMind() {
        return mind;
    }
    
    // ===== SETTERS =====
    public void setMind(int value) {
        this.mind = value;
    }
    
    public void addMind(int value) {
        this.mind += value;
    }
    
    // ===== BÃSICO =====
    /**
     * BÃ´nus de precisÃ£o
     * FÃ³rmula: Mind * 0.5%
     */
    public double getAccuracy() {
        return Math.min(mind * 0.5, 100.0);
    }
    
    /**
     * BÃ´nus de detecÃ§Ã£o (detectar inimigos ocultos)
     * FÃ³rmula: Mind * 1%
     */
    public double getDetectionRange() {
        return mind * 1.0;
    }
    
    /**
     * ResistÃªncia a confusÃ£o
     * FÃ³rmula: Mind * 1%
     */
    public double getConfusionResistance() {
        return Math.min(mind * 1.0, 100.0);
    }
    
    @Override
    public String toString() {
        return "Mnd{" +
                "Mente=" + mind +
                ", PrecisÃ£o=" + String.format("%.1f", getAccuracy()) + "%" +
                ", DetecÃ§Ã£oAlcance=" + String.format("%.1f", getDetectionRange()) + "m" +
                ", ResistÃªnciÃ ConfusÃ£o=" + String.format("%.1f", getConfusionResistance()) + "%" +
                '}';
    }
}
