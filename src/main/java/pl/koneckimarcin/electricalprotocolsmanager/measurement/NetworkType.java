package pl.koneckimarcin.electricalprotocolsmanager.measurement;

public enum NetworkType {

    TNS("TNS"), TNC("TNC"), TNC_S("TNC-S");

    private final String name;

    NetworkType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
