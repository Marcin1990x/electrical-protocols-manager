package pl.koneckimarcin.electricalprotocolsmanager.measurement;

import pl.koneckimarcin.electricalprotocolsmanager.measurement.data.TextData;

public enum Continuity {

    PRESERVED(TextData.continuityEnumText.get(0)), NOTPRESERVED(TextData.continuityEnumText.get(1));
    private final String name;

    Continuity(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
