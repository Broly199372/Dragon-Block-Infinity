package com.dragonblockinfinity.fightstyle;

import java.util.HashMap;
import java.util.Map;

/**
 * Registro central de todos os estilos de luta disponíveis
 * Gerencia instâncias e acesso aos estilos
 */
public class FightStyleRegistry {
    
    private static final Map<FightStyleEnum, IFightStyle> REGISTRY = new HashMap<>();
    
    // Instâncias dos estilos
    private static final Spiritualist SPIRITUALIST_INSTANCE = new Spiritualist();
    private static final Warrior WARRIOR_INSTANCE = new Warrior();
    private static final MartialArts MARTIAL_ARTS_INSTANCE = new MartialArts();
    
    static {
        // Registra todos os estilos
        REGISTRY.put(FightStyleEnum.SPIRITUALIST, SPIRITUALIST_INSTANCE);
        REGISTRY.put(FightStyleEnum.WARRIOR, WARRIOR_INSTANCE);
        REGISTRY.put(FightStyleEnum.MARTIAL_ARTS, MARTIAL_ARTS_INSTANCE);
    }
    
    /**
     * Retorna a instância do estilo baseado no enum
     */
    public static IFightStyle getStyle(FightStyleEnum style) {
        IFightStyle fightStyle = REGISTRY.get(style);
        if (fightStyle == null) {
            throw new IllegalArgumentException("Estilo de luta não registrado: " + style);
        }
        return fightStyle;
    }
    
    /**
     * Retorna o Spiritualist
     */
    public static IFightStyle getSpiritualista() {
        return SPIRITUALIST_INSTANCE;
    }
    
    /**
     * Retorna o Warrior
     */
    public static IFightStyle getWarrior() {
        return WARRIOR_INSTANCE;
    }
    
    /**
     * Retorna o Martial Arts
     */
    public static IFightStyle getMartialArts() {
        return MARTIAL_ARTS_INSTANCE;
    }
    
    /**
     * Retorna todos os estilos registrados
     */
    public static IFightStyle[] getAllStyles() {
        return REGISTRY.values().toArray(new IFightStyle[0]);
    }
    
    /**
     * Retorna informações sobre todos os estilos
     */
    public static String getAllStylesInfo() {
        StringBuilder sb = new StringBuilder("=== ESTILOS DE LUTA DISPONÍVEIS ===\n\n");
        
        for (FightStyleEnum enum_style : FightStyleEnum.values()) {
            IFightStyle style = getStyle(enum_style);
            
            sb.append("【 ").append(style.getStyleName().toUpperCase()).append(" 】\n");
            sb.append("Descrição: ").append(style.getStyleDescription()).append("\n");
            sb.append("Stats Base:\n");
            sb.append("  Str: ").append(style.getBaseStrength())
                    .append(" (×").append(style.getStrengthMultiplier()).append(")\n");
            sb.append("  Con: ").append(style.getBaseConstitution())
                    .append(" (×").append(style.getConstitutionMultiplier()).append(")\n");
            sb.append("  Dex: ").append(style.getBaseDexterity())
                    .append(" (×").append(style.getDexterityMultiplier()).append(")\n");
            sb.append("  Will: ").append(style.getBaseWillpower())
                    .append(" (×").append(style.getWillMultiplier()).append(")\n");
            sb.append("  Spi: ").append(style.getBaseSpirit())
                    .append(" (×").append(style.getSpiritMultiplier()).append(")\n");
            sb.append("  Mnd: ").append(style.getBaseMind())
                    .append(" (×").append(style.getMindMultiplier()).append(")\n\n");
        }
        
        return sb.toString();
    }
    
    /**
     * Retorna info detalhada de um estilo específico
     */
    public static String getStyleInfo(FightStyleEnum styleEnum) {
        IFightStyle style = getStyle(styleEnum);
        
        return "=== " + style.getStyleName().toUpperCase() + " ===\n" +
                "Descrição: " + style.getStyleDescription() + "\n\n" +
                "Stats Base:\n" +
                "  Força (Str): " + style.getBaseStrength() + "\n" +
                "  Constituição (Con): " + style.getBaseConstitution() + "\n" +
                "  Destreza (Dex): " + style.getBaseDexterity() + "\n" +
                "  Vontade (Will): " + style.getBaseWillpower() + "\n" +
                "  Espírito (Spi): " + style.getBaseSpirit() + "\n" +
                "  Mente (Mnd): " + style.getBaseMind() + "\n\n" +
                "Multiplicadores de Stats:\n" +
                "  Str: ×" + style.getStrengthMultiplier() + "\n" +
                "  Con: ×" + style.getConstitutionMultiplier() + "\n" +
                "  Dex: ×" + style.getDexterityMultiplier() + "\n" +
                "  Will: ×" + style.getWillMultiplier() + "\n" +
                "  Spi: ×" + style.getSpiritMultiplier() + "\n" +
                "  Mnd: ×" + style.getMindMultiplier();
    }
}
