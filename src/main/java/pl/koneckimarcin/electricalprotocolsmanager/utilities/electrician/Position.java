package pl.koneckimarcin.electricalprotocolsmanager.utilities.electrician;

import pl.koneckimarcin.electricalprotocolsmanager.measurement.protocolTextData.TextsPL;

public enum Position {

    MEASURER(TextsPL.positionEnumText.get(0)), CHECKER(TextsPL.positionEnumText.get(1));

    private final String name;

    Position(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
