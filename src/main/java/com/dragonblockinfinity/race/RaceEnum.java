package com.dragonblockinfinity.race;

/**
 * Enum para registrar todas as raÃ§as disponÃ­veis
 * Saiyan, Human, Half-Saiyan e Arconsian estÃ£o registradas
 * Android, Majin e Namekian serÃ£o adicionadas quando as texturas forem criadas
 */
public enum RaceEnum {
    SAIYAN("Saiyajin", "Guerreiros poderosos com grande potencial"),
    HUMAN("Humano", "AdaptÃ¡vel e determinado"),
    HALF("Metade-Saiyajin", "HÃ­brido com potencial extraordinÃ¡rio"),
    ARCONSIAN("Arconsiano", "Guerreiro galÃ¡ctico frio e poderoso"),
    
    // TODO: Adicionar quando as texturas forem criadas
    // NAMEKIAN("Namekiano", "MÃ­sticos e regenerativos"),
    // ANDROID("Android", "TecnolÃ³gico e perfeito"),
    // MAJIN("Majin", "MÃ¡gico e poderoso");
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
