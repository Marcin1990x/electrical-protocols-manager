package pl.koneckimarcin.electricalprotocolsmanager.utilities.electrician;

import pl.koneckimarcin.electricalprotocolsmanager.measurement.data.TextData;

public enum Position {

    MEASURER(TextData.positionEnumText.get(0)), CHECKER(TextData.positionEnumText.get(1));

    private final String name;

    Position(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
