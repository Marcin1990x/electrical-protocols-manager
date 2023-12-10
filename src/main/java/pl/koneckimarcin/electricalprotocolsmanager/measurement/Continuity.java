package pl.koneckimarcin.electricalprotocolsmanager.measurement;

import pl.koneckimarcin.electricalprotocolsmanager.measurement.protocolTextData.TextsPL;

public enum Continuity {

    PRESERVED(TextsPL.continuityEnumText.get(0)), NOTPRESERVED(TextsPL.continuityEnumText.get(1));
    private final String name;

    Continuity(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
