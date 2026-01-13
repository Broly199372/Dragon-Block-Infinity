package com.dragonblockinfinity.player;

import com.dragonblockinfinity.status.Str;
import com.dragonblockinfinity.status.Dex;
import com.dragonblockinfinity.status.Con;
import com.dragonblockinfinity.status.Will;
import com.dragonblockinfinity.status.Spi;
import com.dragonblockinfinity.status.Mnd;

/**
 * Dados completos do jogador em combate
 */
public class PlayerData {
    
    private String playerName;
    private Str strength;
    private Dex dexterity;
    private Con constitution;
    private Will willpower;
    private Spi spirit;
    private Mnd mind;
    private PlayerKiManager kiManager;
    
    public PlayerData(String playerName, int baseStats) {
        this.playerName = playerName;
        
        // Inicializa todos os stats
        this.strength = new Str(baseStats);
        this.dexterity = new Dex(baseStats);
        this.constitution = new Con(baseStats);
        this.willpower = new Will(baseStats, baseStats);
        this.spirit = new Spi(baseStats);
        this.mind = new Mnd(baseStats);
        this.kiManager = new PlayerKiManager(baseStats * 10);
    }
    
    // ===== GETTERS =====
    public String getPlayerName() {
        return playerName;
    }
    
    public Str getStrength() {
        return strength;
    }
    
    public Dex getDexterity() {
        return dexterity;
    }
    
    public Con getConstitution() {
        return constitution;
    }
    
    public Will getWillpower() {
        return willpower;
    }
    
    public Spi getSpirit() {
        return spirit;
    }
    
    public Mnd getMind() {
        return mind;
    }
    
    public PlayerKiManager getKiManager() {
        return kiManager;
    }
    
    // ===== TICK =====
    public void tick() {
        kiManager.tickRegeneration();
        constitution.tickRegeneration();
    }
    
    @Override
    public String toString() {
        return "PlayerData{" +
                "name='" + playerName + '\'' +
                ", Ki=" + kiManager +
                ", Stats=[Str=" + strength.getStrength() +
                ", Con=" + constitution.getConstitution() +
                ", Dex=" + dexterity.getDexterity() +
                ", Will=" + willpower.getWillpower() +
                ", Spi=" + spirit.getSpirit() +
                ", Mnd=" + mind.getMind() +
                "]}";
    }
}
