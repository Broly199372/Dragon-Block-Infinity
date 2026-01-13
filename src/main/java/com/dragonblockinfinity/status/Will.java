package com.dragonblockinfinity.status;

/**
 * Classe para gerenciar Vontade (Will)
 * Will controla o dano de Ki (ataques de energia) e resistência mental
 */
public class Will {
    private int willpower;      // Vontade - aumenta dano de Ki
    private int spiritLevel;    // Nível de espírito (referência)
    
    public Will(int willpower, int spirit) {
        this.willpower = willpower;
        this.spiritLevel = spirit;
    }
    
    // ===== GETTERS =====
    public int getWillpower() {
        return willpower;
    }
    
    public int getSpiritLevel() {
        return spiritLevel;
    }
    
    // ===== SETTERS =====
    public void setWillpower(int value) {
        this.willpower = value;
    }
    
    public void setSpiritLevel(int spirit) {
        this.spiritLevel = spirit;
    }
    
    public void addWillpower(int value) {
        this.willpower += value;
    }
    
    // ===== DANO DE KI =====
    /**
     * Calcula dano base de ataque de Ki (Ki Blast, Foice Attack, etc)
     * Fórmula: (Will * 2) + 10
     */
    public int getKiBlastDamage() {
        return (willpower * 2) + 10;
    }
    
    /**
     * Calcula custo de Ki para executar um ataque
     * Fórmula: 20 + (Will / 2)
     */
    public int getKiCost() {
        return 20 + (willpower / 2);
    }
    
    /**
     * Calcula dano do Foice Attack (ataque mais forte)
     * Custa mais Ki e faz mais dano
     * Fórmula: Dano de Ki * 2.5
     */
    public int getFoiceAttackDamage() {
        return (int)(getKiBlastDamage() * 2.5);
    }
    
    /**
     * Calcula custo de Ki do Foice Attack
     * Custa 2.5x o normal
     */
    public int getFoiceAttackKiCost() {
        return (int)(getKiCost() * 2.5);
    }
    
    // ===== RESISTÊNCIA MENTAL =====
    /**
     * Resistência a efeitos de controle mental
     * Fórmula: Will * 2%
     */
    public double getMentalResistance() {
        return Math.min(willpower * 2.0, 100.0);
    }
    
    /**
     * Chance de resistir a um efeito negativo
     */
    public boolean resistNegativeEffect() {
        return Math.random() * 100 < getMentalResistance();
    }
    
    @Override
    public String toString() {
        return "Will{" +
                "Vontade=" + willpower +
                ", DanoKi=" + getKiBlastDamage() +
                ", CustoKi=" + getKiCost() +
                ", DanoFoice=" + getFoiceAttackDamage() +
                ", CustoFoice=" + getFoiceAttackKiCost() +
                ", ResistênciaMental=" + String.format("%.1f", getMentalResistance()) + "%" +
                '}';
    }
}
