package com.dragonblockinfinity.fightstyle;

/**
 * Enum para registrar todos os estilos de luta disponíveis
 */
public enum FightStyleEnum {
    SPIRITUALIST("Espiritualista", "Focado em Ki e ataques mágicos"),
    WARRIOR("Guerreiro", "Focado em força e resistência corporal"),
    MARTIAL_ARTS("Artes Marciais", "Estilo equilibrado e versátil");
    
    private final String displayName;
    private final String description;
    
    FightStyleEnum(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    public String getDescription() {
        return description;
    }
}
