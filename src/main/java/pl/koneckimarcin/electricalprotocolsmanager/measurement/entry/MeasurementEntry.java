package pl.koneckimarcin.electricalprotocolsmanager.measurement.entry;

import pl.koneckimarcin.electricalprotocolsmanager.measurement.Result;

import java.util.List;

public abstract class MeasurementEntry {

    private int id;
    private String symbol;
    private Result result;

    public MeasurementEntry(int id, String symbol) {
        this.id = id;
        this.symbol = symbol;
    }

    public int getId() {
        return id;
    }

    public String getSymbol() {
        return symbol;
    }

    public Result getResult() {
        if (result != null) return result;
        else return Result.NONE;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public List<Object> getEntryResultList() {

        return List.of(this.id, this.symbol, this.result.getName());
    }
}
