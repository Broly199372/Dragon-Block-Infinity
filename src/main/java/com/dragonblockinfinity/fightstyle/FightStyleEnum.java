package com.dragonblockinfinity.fightstyle;

/**
 * Enum para registrar todos os estilos de luta disponÃ­veis
 */
public enum FightStyleEnum {
    SPIRITUALIST("Espiritualista", "Focado em Ki e ataques mÃ¡gicos"),
    WARRIOR("Guerreiro", "Focado em forÃ§a e resistÃªncia corporal"),
    MARTIAL_ARTS("Artes Marciais", "Estilo equilibrado e versÃ¡til");
    
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
