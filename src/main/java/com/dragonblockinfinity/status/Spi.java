package com.dragonblockinfinity.status;

/**
 * Classe para gerenciar Espírito (Spi) - Ki Máximo
 * Spi controla a quantidade de Ki disponível
 * Habilidade Secreta: OPRESSÃO DE KI
 * - Causa lentidão no chão
 * - Impede o player de voar se o Spi for muito maior
 */
public class Spi {
    private int spirit;           // Espírito - Ki máximo
    private int currentKi;        // Ki atual
    private int maxKi;            // Ki máximo
    private double kiRegenRate;   // Taxa de regeneração de Ki por segundo
    
    // ===== HABILIDADE SECRETA: OPRESSÃO DE KI =====
    private int oppressionCharge;      // Carga de opressão (0-100)
    private int maxOppressionCharge;   // Máximo de carga (100)
    private boolean isOppressing;      // Está oprimindo?
    private double slowdownIntensity;  // Intensidade de lentidão (0-100%)
    
    public Spi(int spirit) {
        this.spirit = spirit;
        this.maxKi = spirit * 10;  // Ki máximo = Spi * 10
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
    
    // ===== HABILIDADE SECRETA: OPRESSÃO DE KI =====
    /**
     * HABILIDADE SECRETA - OPRESSÃO DE KI
     * 
     * A opressão de Ki funciona baseada na diferença de Spi entre jogadores
     * Quanto maior o Spi do jogador 1 em relação ao do jogador 2,
     * mais ele consegue oprimir o outro
     * 
     * Efeitos:
     * - Causa lentidão no chão (até 100% de redução de velocidade)
     * - Impede o player oprimido de voar se a opressão for muito forte
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
     * Calcula o nível de opressão baseado na diferença de Spi
     * Fórmula: (Spi próprio - Spi inimigo) / Spi próprio * 100
     */
    public double calculateOppression(int enemySpi) {
        if (enemySpi <= 0) return 100.0;
        
        double difference = spirit - enemySpi;
        if (difference <= 0) return 0.0; // Sem opressão se Spi for menor
        
        return Math.min(100.0, (difference / spirit) * 100);
    }
    
    /**
     * Ativa a opressão de Ki contra um inimigo
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
     * Desativa a opressão de Ki
     */
    public void deactivateOppressionField() {
        isOppressing = false;
        slowdownIntensity = 0.0;
        oppressionCharge = 0;
    }
    
    /**
     * Mantém a opressão ativa consumindo Ki continuamente
     * Quanto mais forte a opressão, mais Ki consome por tick
     */
    public void tickOppressionField() {
        if (!isOppressing) return;
        
        // Consome Ki baseado na intensidade da opressão
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
     * Calcula quanto o player é desacelerado (0-1, onde 1 é 100% de lentidão)
     * Quanto maior a opressão, mais lento fica
     */
    public double getMovementSpeedMultiplier(int enemySpi) {
        if (!isOppressing) return 1.0;
        
        double oppression = calculateOppression(enemySpi);
        // Reduz velocidade de 0% até 80% baseado na opressão
        return 1.0 - (oppression / 100.0 * 0.8);
    }
    
    /**
     * Verifica se o player pode voar com essa opressão
     * Se a opressão for >= 50%, não consegue voar
     */
    public boolean canFly(int enemySpi) {
        if (!isOppressing) return true;
        
        double oppression = calculateOppression(enemySpi);
        return oppression < 50.0; // Só consegue voar se opressão < 50%
    }
    
    /**
     * Reduz a taxa de regen de Ki do inimigo
     */
    public double getEnemyKiRegenPenalty(int enemySpi) {
        if (!isOppressing) return 1.0;
        
        double oppression = calculateOppression(enemySpi);
        // Reduz regen de 0% até 70% baseado na opressão
        return 1.0 - (oppression / 100.0 * 0.7);
    }
    
    /**
     * Mostra informações da opressão
     */
    public String getOppressionInfo(int enemySpi) {
        double oppression = calculateOppression(enemySpi);
        return "=== OPRESSÃO DE KI (HABILIDADE SECRETA) ===\n" +
                "Spi Próprio: " + spirit + " | Spi Inimigo: " + enemySpi + "\n" +
                "Nível de Opressão: " + String.format("%.1f", oppression) + "%\n" +
                "Status: " + (isOppressing ? "ATIVA" : "Inativa") + "\n" +
                "Lentidão do Inimigo: " + String.format("%.1f", (1.0 - getMovementSpeedMultiplier(enemySpi)) * 100) + "%\n" +
                "Pode Voar: " + (canFly(enemySpi) ? "SIM" : "NÃO") + "\n" +
                "Regen de Ki Reduzida: " + String.format("%.1f", (1.0 - getEnemyKiRegenPenalty(enemySpi)) * 100) + "%";
    }
    
    @Override
    public String toString() {
        return "Spi{" +
                "Espírito=" + spirit +
                ", Ki=" + currentKi + "/" + maxKi +
                " (" + String.format("%.1f", getKiPercentage() * 100) + "%)" +
                ", RegenRate=" + kiRegenRate + "%" +
                ", Opressão=" + (isOppressing ? "ATIVA (" + (int)slowdownIntensity + "%)" : "Inativa") +
                '}';
    }
}
