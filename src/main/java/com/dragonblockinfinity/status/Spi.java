package com.dragonblockinfinity.status;

/**
 * Classe para gerenciar EspÃ­rito (Spi) - Ki MÃ¡ximo
 * Spi controla a quantidade de Ki disponÃ­vel
 * Habilidade Secreta: OPRESSÃO DE KI
 * - Causa lentidÃ£o no chÃ£o
 * - Impede o player de voar se o Spi for muito maior
 */
public class Spi {
    private int spirit;           // EspÃ­rito - Ki mÃ¡ximo
    private int currentKi;        // Ki atual
    private int maxKi;            // Ki mÃ¡ximo
    private double kiRegenRate;   // Taxa de regeneraÃ§Ã£o de Ki por segundo
    
    // ===== HABILIDADE SECRETA: OPRESSÃO DE KI =====
    private int oppressionCharge;      // Carga de opressÃ£o (0-100)
    private int maxOppressionCharge;   // MÃ¡ximo de carga (100)
    private boolean isOppressing;      // EstÃ¡ oprimindo?
    private double slowdownIntensity;  // Intensidade de lentidÃ£o (0-100%)
    
    public Spi(int spirit) {
        this.spirit = spirit;
        this.maxKi = spirit * 10;  // Ki mÃ¡ximo = Spi * 10
        this.currentKi = maxKi;
        this.kiRegenRate = 0.5;    // 0.5% de regen por tick
        
        // Habilidade Secreta
        this.oppressionCharge = 0;
        this.maxOppressionCharge = 100;
        this.isOppressing = false;
        this.slowdownIntensity = 0.0;
    }
    
    // ===== GETTERS =====
    public int getSpirit() {
        return spirit;
    }
    
    public int getCurrentKi() {
        return currentKi;
    }
    
    public int getMaxKi() {
        return maxKi;
    }
    
    public double getKiRegenRate() {
        return kiRegenRate;
    }
    
    // ===== SETTERS =====
    public void setSpirit(int spirit) {
        this.spirit = spirit;
        this.maxKi = spirit * 10;
        if (currentKi > maxKi) {
            currentKi = maxKi;
        }
    }
    
    public void setKiRegenRate(double rate) {
        this.kiRegenRate = Math.max(0, Math.min(rate, 1.0));
    }
    
    public void addSpirit(int value) {
        setSpirit(spirit + value);
    }
    
    // ===== KI MANAGEMENT =====
    public void consumeKi(int amount) {
        currentKi = Math.max(0, currentKi - amount);
    }
    
    public void restoreKi(int amount) {
        currentKi = Math.min(currentKi + amount, maxKi);
    }
    
    public void regenerateKi() {
        int regenAmount = (int)(maxKi * (kiRegenRate / 100.0));
        restoreKi(regenAmount);
    }
    
    public boolean hasKi(int required) {
        return currentKi >= required;
    }
    
    public double getKiPercentage() {
        return (double) currentKi / maxKi;
    }
    
    // ===== HABILIDADE SECRETA: OPRESSÃO DE KI =====
    /**
     * HABILIDADE SECRETA - OPRESSÃO DE KI
     * 
     * A opressÃ£o de Ki funciona baseada na diferenÃ§a de Spi entre jogadores
     * Quanto maior o Spi do jogador 1 em relaÃ§Ã£o ao do jogador 2,
     * mais ele consegue oprimir o outro
     * 
     * Efeitos:
     * - Causa lentidÃ£o no chÃ£o (atÃ© 100% de reduÃ§Ã£o de velocidade)
     * - Impede o player oprimido de voar se a opressÃ£o for muito forte
     * - Reduz a velocidade de regen de Ki do oprimido
     */
    
    public int getOppressionCharge() {
        return oppressionCharge;
    }
    
    public boolean isOppressing() {
        return isOppressing;
    }
    
    public double getSlowdownIntensity() {
        return slowdownIntensity;
    }
    
    /**
     * Calcula o nÃ­vel de opressÃ£o baseado na diferenÃ§a de Spi
     * FÃ³rmula: (Spi prÃ³prio - Spi inimigo) / Spi prÃ³prio * 100
     */
    public double calculateOppression(int enemySpi) {
        if (enemySpi <= 0) return 100.0;
        
        double difference = spirit - enemySpi;
        if (difference <= 0) return 0.0; // Sem opressÃ£o se Spi for menor
        
        return Math.min(100.0, (difference / spirit) * 100);
    }
    
    /**
     * Ativa a opressÃ£o de Ki contra um inimigo
     * Requer bastante Ki para manter ativa
     */
    public boolean activateOppressionField(int enemySpi) {
        if (currentKi < 50) {
            return false; // Precisa de pelo menos 50 de Ki
        }
        
        isOppressing = true;
        slowdownIntensity = calculateOppression(enemySpi);
        return true;
    }
    
    /**
     * Desativa a opressÃ£o de Ki
     */
    public void deactivateOppressionField() {
        isOppressing = false;
        slowdownIntensity = 0.0;
        oppressionCharge = 0;
    }
    
    /**
     * MantÃ©m a opressÃ£o ativa consumindo Ki continuamente
     * Quanto mais forte a opressÃ£o, mais Ki consome por tick
     */
    public void tickOppressionField() {
        if (!isOppressing) return;
        
        // Consome Ki baseado na intensidade da opressÃ£o
        int kiCost = (int)(slowdownIntensity * 0.5);
        consumeKi(kiCost);
        
        // Acumula carga
        oppressionCharge = Math.min(maxOppressionCharge, (int)slowdownIntensity);
        
        // Se acabar o Ki, deativa automaticamente
        if (currentKi <= 0) {
            deactivateOppressionField();
        }
    }
    
    /**
     * Calcula quanto o player Ã© desacelerado (0-1, onde 1 Ã© 100% de lentidÃ£o)
     * Quanto maior a opressÃ£o, mais lento fica
     */
    public double getMovementSpeedMultiplier(int enemySpi) {
        if (!isOppressing) return 1.0;
        
        double oppression = calculateOppression(enemySpi);
        // Reduz velocidade de 0% atÃ© 80% baseado na opressÃ£o
        return 1.0 - (oppression / 100.0 * 0.8);
    }
    
    /**
     * Verifica se o player pode voar com essa opressÃ£o
     * Se a opressÃ£o for >= 50%, nÃ£o consegue voar
     */
    public boolean canFly(int enemySpi) {
        if (!isOppressing) return true;
        
        double oppression = calculateOppression(enemySpi);
        return oppression < 50.0; // SÃ³ consegue voar se opressÃ£o < 50%
    }
    
    /**
     * Reduz a taxa de regen de Ki do inimigo
     */
    public double getEnemyKiRegenPenalty(int enemySpi) {
        if (!isOppressing) return 1.0;
        
        double oppression = calculateOppression(enemySpi);
        // Reduz regen de 0% atÃ© 70% baseado na opressÃ£o
        return 1.0 - (oppression / 100.0 * 0.7);
    }
    
    /**
     * Mostra informaÃ§Ãµes da opressÃ£o
     */
    public String getOppressionInfo(int enemySpi) {
        double oppression = calculateOppression(enemySpi);
        return "=== OPRESSÃO DE KI (HABILIDADE SECRETA) ===\n" +
                "Spi PrÃ³prio: " + spirit + " | Spi Inimigo: " + enemySpi + "\n" +
                "NÃ­vel de OpressÃ£o: " + String.format("%.1f", oppression) + "%\n" +
                "Status: " + (isOppressing ? "ATIVA" : "Inativa") + "\n" +
                "LentidÃ£o do Inimigo: " + String.format("%.1f", (1.0 - getMovementSpeedMultiplier(enemySpi)) * 100) + "%\n" +
                "Pode Voar: " + (canFly(enemySpi) ? "SIM" : "NÃO") + "\n" +
                "Regen de Ki Reduzida: " + String.format("%.1f", (1.0 - getEnemyKiRegenPenalty(enemySpi)) * 100) + "%";
    }
    
    @Override
    public String toString() {
        return "Spi{" +
                "EspÃ­rito=" + spirit +
                ", Ki=" + currentKi + "/" + maxKi +
                " (" + String.format("%.1f", getKiPercentage() * 100) + "%)" +
                ", RegenRate=" + kiRegenRate + "%" +
                ", OpressÃ£o=" + (isOppressing ? "ATIVA (" + (int)slowdownIntensity + "%)" : "Inativa") +
                '}';
    }
}
