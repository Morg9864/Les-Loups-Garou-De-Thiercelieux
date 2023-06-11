public enum Team {
    VILLAGE,
    LOUPS,
    NEUTRE;

    public String toString() {
        switch (this) {
            case VILLAGE:
                return "VILLAGE";
            case LOUPS:
                return "LOUPS";
            case NEUTRE:
                return "NEUTRE";
            default:
                return "UNKNOWN";
        }
    }

    public static Team fromString(String s) {
        switch (s) {
            case "VILLAGE":
                return VILLAGE;
            case "LOUPS":
                return LOUPS;
            case "NEUTRE":
                return NEUTRE;
            default:
                return null;
        }
    }
}
