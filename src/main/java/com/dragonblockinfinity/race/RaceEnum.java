package com.dragonblockinfinity.race;

/**
 * Enum para registrar todas as raças disponíveis
 * Saiyan, Human, Half-Saiyan e Arconsian estão registradas
 * Android, Majin e Namekian serão adicionadas quando as texturas forem criadas
 */
public enum RaceEnum {
    SAIYAN("Saiyajin", "Guerreiros poderosos com grande potencial"),
    HUMAN("Humano", "Adaptável e determinado"),
    HALF("Metade-Saiyajin", "Híbrido com potencial extraordinário"),
    ARCONSIAN("Arconsiano", "Guerreiro galáctico frio e poderoso"),
    
    // TODO: Adicionar quando as texturas forem criadas
    // NAMEKIAN("Namekiano", "Místicos e regenerativos"),
    // ANDROID("Android", "Tecnológico e perfeito"),
    // MAJIN("Majin", "Mágico e poderoso");
    ;
    
    private final String displayName;
    private final String description;
    
    RaceEnum(String displayName, String description) {
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
