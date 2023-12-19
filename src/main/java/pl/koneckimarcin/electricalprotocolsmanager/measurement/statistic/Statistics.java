package pl.koneckimarcin.electricalprotocolsmanager.measurement.statistic;

import pl.koneckimarcin.electricalprotocolsmanager.measurement.Result;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTnc.entry.CircuitInsulationResistanceTncEntry;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTns.entry.CircuitInsulationResistanceTnsEntry;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.entry.MeasurementEntry;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.main.MeasurementMain;

import java.util.List;

public class Statistics {

    public int totalMeasuringPoints;
    public int totalPositiveResults;
    public int totalNegativeResults;
    public int measuredObjects;
    public int totalOnePhaseCircuits;
    public int totalThreePhaseCircuits;
    public int totalNoneResults;

    public Statistics(List<MeasurementMain> measurements) {
        setStatisticsValues(measurements);
    }
    public void countNegativeResults(){
        this.totalNegativeResults = this.totalMeasuringPoints - this.totalPositiveResults;
    }
    private void setStatisticsValues(List<MeasurementMain> measurements) {

        for (MeasurementMain measurement : measurements) {
            this.totalMeasuringPoints += countMeasuringPoints(measurement);
            this.totalPositiveResults += countPositiveResults(measurement);
            if (measurement.getMeasurementName().contains("Badanie rezystancji izolacji obwodów")) {
                this.totalOnePhaseCircuits += countOneAndThreePhaseCircuits(measurement)[0];
                this.totalThreePhaseCircuits += countOneAndThreePhaseCircuits(measurement)[1];
            }
            if (measurement.getMeasurementName().contains("gruntu")) {
                this.totalNoneResults += countNoneResults(measurement);
            }
        }
        this.measuredObjects = measurements.size();
        this.totalNegativeResults = this.totalMeasuringPoints - this.totalPositiveResults;
    }
    private int countPositiveResults(MeasurementMain measurement) {

        int positiveResults = 0;

        for (MeasurementEntry entry : measurement.getMeasurementEntries()) {
            if (entry.getResult() == Result.POSITIVE) {
                positiveResults++;
            }
        }
        return positiveResults;
    }
    private int countMeasuringPoints(MeasurementMain measurement) {
        return measurement.getMeasurementEntries().size();
    }
    private int[] countOneAndThreePhaseCircuits(MeasurementMain measurement) {

        int totalOnePhaseCircuits = 0;
        int totalThreePhaseCircuits = 0;

        for (MeasurementEntry entry : measurement.getMeasurementEntries()) {
            if (isOnePhaseCircuit(entry, measurement.getMeasurementName()))
            {
                totalOnePhaseCircuits++;
            } else {
                totalThreePhaseCircuits++;
            }
        }
        return new int[]{totalOnePhaseCircuits, totalThreePhaseCircuits};
    }
    private boolean isOnePhaseCircuit(MeasurementEntry entry, String measurementName) {

        if(measurementName.contains("TN-S")) {
            return ((CircuitInsulationResistanceTnsEntry) entry).getL1l2() == 0 ||
                    ((CircuitInsulationResistanceTnsEntry) entry).getL2l3() == 0 ||
                    ((CircuitInsulationResistanceTnsEntry) entry).getL3l1() == 0;
        } else {
            return ((CircuitInsulationResistanceTncEntry) entry).getL1l2() == 0 ||
                    ((CircuitInsulationResistanceTncEntry) entry).getL2l3() == 0 ||
                    ((CircuitInsulationResistanceTncEntry) entry).getL3l1() == 0;
        }
    }
    private int countNoneResults(MeasurementMain measurement) {

        int totalNoneResults = 0;

        for (MeasurementEntry entry : measurement.getMeasurementEntries()) {
            if (entry.getResult() == Result.NONE) {
                totalNoneResults++;
            }
        }
        return totalNoneResults;
    }
}
