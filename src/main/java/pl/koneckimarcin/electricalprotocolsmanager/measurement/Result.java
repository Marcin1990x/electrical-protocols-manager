package pl.koneckimarcin.electricalprotocolsmanager.measurement;

import pl.koneckimarcin.electricalprotocolsmanager.measurement.protocolTextData.TextsPL;

public enum Result {

    POSITIVE(TextsPL.resultEnumText.get(0)), NEGATIVE(TextsPL.resultEnumText.get(1)), NONE("None");
    private final String name;

    Result(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
