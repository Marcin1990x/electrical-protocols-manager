package pl.koneckimarcin.electricalprotocolsmanager.measurement;

import java.util.List;

public abstract class MeasurementEntry {

    private int id;
    private String symbol;
    private Result result;

    public MeasurementEntry(int id, String symbol, Result result) {
        this.id = id;
        this.symbol = symbol;
        this.result = result;
    }

    public int getId() {
        return id;
    }

    public String getSymbol() {
        return symbol;
    }

    public Result getResult() {
        return result;
    }

    public List<Object> getEntryResultList() {

        return List.of(this.id, this.symbol, this.result.getName());
    }
}
