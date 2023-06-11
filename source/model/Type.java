package source.model;
public enum Type {
    VILLAGEOIS,
    VILLAGEOIS_SPECIAL,
    LOUP_GAROU,
    SOLITAIRE,
    AMBIGU,
    SPECIFIQUE;

    public String toString() {
        switch (this) {
            case VILLAGEOIS:
                return "VILLAGEOIS";
            case VILLAGEOIS_SPECIAL:
                return "VILLAGEOIS_SPECIAL";
            case LOUP_GAROU:
                return "LOUP_GAROU";
            case SOLITAIRE:
                return "SOLITAIRE";
            case AMBIGU:
                return "AMBIGU";
            case SPECIFIQUE:
                return "SPECIFIQUE";
            default:
                return "UNKNOWN";
        }
    }

    public static Type fromString(String s) {
        switch (s) {
            case "VILLAGEOIS":
                return VILLAGEOIS;
            case "VILLAGEOIS_SPECIAL":
                return VILLAGEOIS_SPECIAL;
            case "LOUP_GAROU":
                return LOUP_GAROU;
            case "SOLITAIRE":
                return SOLITAIRE;
            case "AMBIGU":
                return AMBIGU;
            case "SPECIFIQUE":
                return SPECIFIQUE;
            default:
                return null;
        }
    }
}
