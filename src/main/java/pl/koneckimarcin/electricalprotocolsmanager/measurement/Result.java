package pl.koneckimarcin.electricalprotocolsmanager.measurement;

import pl.koneckimarcin.electricalprotocolsmanager.measurement.data.TextData;

public enum Result {

    POSITIVE(TextData.resultEnumText.get(0)), NEGATIVE(TextData.resultEnumText.get(1));
    private final String name;

    Result(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
