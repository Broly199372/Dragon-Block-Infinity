package com.dragonblockinfinity.fightstyle;

import java.util.HashMap;
import java.util.Map;

/**
 * Registro central de todos os estilos de luta disponÃ­veis
 * Gerencia instÃ¢ncias e acesso aos estilos
 */
public class FightStyleRegistry {
    
    private static final Map<FightStyleEnum, IFightStyle> REGISTRY = new HashMap<>();
    
    // InstÃ¢ncias dos estilos
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
     * Retorna a instÃ¢ncia do estilo baseado no enum
     */
    public static IFightStyle getStyle(FightStyleEnum style) {
        IFightStyle fightStyle = REGISTRY.get(style);
        if (fightStyle == null) {
            throw new IllegalArgumentException("Estilo de luta nÃ£o registrado: " + style);
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
     * Retorna informaÃ§Ãµes sobre todos os estilos
     */
    public static String getAllStylesInfo() {
        StringBuilder sb = new StringBuilder("=== ESTILOS DE LUTA DISPONÃVEIS ===\n\n");
        
        for (FightStyleEnum enum_style : FightStyleEnum.values()) {
            IFightStyle style = getStyle(enum_style);
            
            sb.append("ã ").append(style.getStyleName().toUpperCase()).append(" ã\n");
            sb.append("DescriÃ§Ã£o: ").append(style.getStyleDescription()).append("\n");
            sb.append("Stats Base:\n");
            sb.append("  Str: ").append(style.getBaseStrength())
                    .append(" (Ã").append(style.getStrengthMultiplier()).append(")\n");
            sb.append("  Con: ").append(style.getBaseConstitution())
                    .append(" (Ã").append(style.getConstitutionMultiplier()).append(")\n");
            sb.append("  Dex: ").append(style.getBaseDexterity())
                    .append(" (Ã").append(style.getDexterityMultiplier()).append(")\n");
            sb.append("  Will: ").append(style.getBaseWillpower())
                    .append(" (Ã").append(style.getWillMultiplier()).append(")\n");
            sb.append("  Spi: ").append(style.getBaseSpirit())
                    .append(" (Ã").append(style.getSpiritMultiplier()).append(")\n");
            sb.append("  Mnd: ").append(style.getBaseMind())
                    .append(" (Ã").append(style.getMindMultiplier()).append(")\n\n");
        }
        
        return sb.toString();
    }
    
    /**
     * Retorna info detalhada de um estilo especÃ­fico
     */
    public static String getStyleInfo(FightStyleEnum styleEnum) {
        IFightStyle style = getStyle(styleEnum);
        
        return "=== " + style.getStyleName().toUpperCase() + " ===\n" +
                "DescriÃ§Ã£o: " + style.getStyleDescription() + "\n\n" +
                "Stats Base:\n" +
                "  ForÃ§a (Str): " + style.getBaseStrength() + "\n" +
                "  ConstituiÃ§Ã£o (Con): " + style.getBaseConstitution() + "\n" +
                "  Destreza (Dex): " + style.getBaseDexterity() + "\n" +
                "  Vontade (Will): " + style.getBaseWillpower() + "\n" +
                "  EspÃ­rito (Spi): " + style.getBaseSpirit() + "\n" +
                "  Mente (Mnd): " + style.getBaseMind() + "\n\n" +
                "Multiplicadores de Stats:\n" +
                "  Str: Ã" + style.getStrengthMultiplier() + "\n" +
                "  Con: Ã" + style.getConstitutionMultiplier() + "\n" +
                "  Dex: Ã" + style.getDexterityMultiplier() + "\n" +
                "  Will: Ã" + style.getWillMultiplier() + "\n" +
                "  Spi: Ã" + style.getSpiritMultiplier() + "\n" +
                "  Mnd: Ã" + style.getMindMultiplier();
    }
}
