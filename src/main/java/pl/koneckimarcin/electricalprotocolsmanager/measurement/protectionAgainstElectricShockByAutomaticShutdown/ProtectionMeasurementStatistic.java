package pl.koneckimarcin.electricalprotocolsmanager.measurement.protectionAgainstElectricShockByAutomaticShutdown;

import pl.koneckimarcin.electricalprotocolsmanager.measurement.MeasurementMainStatistic;

public class ProtectionMeasurementStatistic extends MeasurementMainStatistic {

    private final String measurementName;
    private int totalMeasuringPoints;
    private int totalPositiveResults;

    public ProtectionMeasurementStatistic(String measurementName) {
        super();
        this.measurementName = measurementName;
        this.totalMeasuringPoints = 0;
        this.totalPositiveResults = 0;
    }

    public void addMeasuringPoint() {
        this.totalMeasuringPoints ++;
    }
    public void addPositiveResult() {
        this.totalPositiveResults ++;
    }

    public int getTotalMeasuringPoints() {
        return totalMeasuringPoints;
    }

    public int getTotalPositiveResults() {
        return totalPositiveResults;
    }

    @Override
    public String toString() {
        return "ProtectionMeasurementStatistic{" +
                "name='" + measurementName + '\'' +
                ", totalMeasuringPoints=" + totalMeasuringPoints +
                ", totalPositiveResults=" + totalPositiveResults +
                '}';
    }


}
