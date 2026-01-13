package com.dragonblockinfinity.status;

/**
 * Classe para gerenciar Mente (Mnd) - BÁSICO
 * Mnd controla inteligência, precisão e detecção
 */
public class Mnd {
    private int mind;  // Mente - inteligência
    
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
    
    // ===== BÁSICO =====
    /**
     * Bônus de precisão
     * Fórmula: Mind * 0.5%
     */
    public double getAccuracy() {
        return Math.min(mind * 0.5, 100.0);
    }
    
    /**
     * Bônus de detecção (detectar inimigos ocultos)
     * Fórmula: Mind * 1%
     */
    public double getDetectionRange() {
        return mind * 1.0;
    }
    
    /**
     * Resistência a confusão
     * Fórmula: Mind * 1%
     */
    public double getConfusionResistance() {
        return Math.min(mind * 1.0, 100.0);
    }
    
    @Override
    public String toString() {
        return "Mnd{" +
                "Mente=" + mind +
                ", Precisão=" + String.format("%.1f", getAccuracy()) + "%" +
                ", DetecçãoAlcance=" + String.format("%.1f", getDetectionRange()) + "m" +
                ", ResistênciàConfusão=" + String.format("%.1f", getConfusionResistance()) + "%" +
                '}';
    }
}
