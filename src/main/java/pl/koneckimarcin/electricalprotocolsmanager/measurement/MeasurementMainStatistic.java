package pl.koneckimarcin.electricalprotocolsmanager.measurement;

public abstract class MeasurementMainStatistic {

    private int totalMeasuringPoints;
    private int totalPositiveResults;

    public int getTotalMeasuringPoints() {
        return totalMeasuringPoints;
    }
    public int getTotalPositiveResults() {
        return totalPositiveResults;
    }
}
