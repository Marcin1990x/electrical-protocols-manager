package pl.koneckimarcin.electricalprotocolsmanager.measurement.statistic;

import pl.koneckimarcin.electricalprotocolsmanager.measurement.Result;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTnc.entry.CircuitInsulationResistanceTncEntry;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTns.entry.CircuitInsulationResistanceTnsEntry;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.entry.MeasurementEntry;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.main.MeasurementMain;

import java.util.List;

import static pl.koneckimarcin.electricalprotocolsmanager.measurement.protocolTextData.TextsPL.measurementsMainNames;
import static pl.koneckimarcin.electricalprotocolsmanager.measurement.protocolTextData.TextsPL.statisticsText;

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

    private void setStatisticsValues(List<MeasurementMain> measurements) {

        for (MeasurementMain measurement : measurements) {
            this.totalMeasuringPoints += countMeasuringPoints(measurement);
            this.totalPositiveResults += countPositiveResults(measurement);
            if (isInsulationMeasurement(measurement)) {
                this.totalOnePhaseCircuits += countOneAndThreePhaseCircuits(measurement)[0];
                this.totalThreePhaseCircuits += countOneAndThreePhaseCircuits(measurement)[1];
            }
            if (isSoilMeasurement(measurement)) {
                this.totalNoneResults += countNoneResults(measurement);
            }
        }
        this.measuredObjects = measurements.size();
        this.totalNegativeResults = this.totalMeasuringPoints - this.totalPositiveResults;
    }

    private int countPositiveResults(MeasurementMain measurement) {

        int positiveResults = 0;

        for (MeasurementEntry entry : measurement.getMeasurementEntries()) {
            if (isResult(entry, Result.POSITIVE)) {
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
            if (isOnePhaseCircuit(entry, measurement.getMeasurementName())) {
                totalOnePhaseCircuits++;
            } else {
                totalThreePhaseCircuits++;
            }
        }
        return new int[]{totalOnePhaseCircuits, totalThreePhaseCircuits};
    }

    private boolean isOnePhaseCircuit(MeasurementEntry entry, String measurementName) {

        if (isInsulationTnsMeasurement(measurementName)) {
            return ((CircuitInsulationResistanceTnsEntry) entry).getL1l2() != 0;
        } else { // TNC
            return ((CircuitInsulationResistanceTncEntry) entry).getL1l2() != 0;
        }
    }

    private int countNoneResults(MeasurementMain measurement) {

        int totalNoneResults = 0;

        for (MeasurementEntry entry : measurement.getMeasurementEntries()) {
            if (isResult(entry, Result.NONE)) {
                totalNoneResults++;
            }
        }
        return totalNoneResults;
    }

    public String getTotalMeasuringPointsWithDescription() {
        return statisticsText.get(0) + totalMeasuringPoints;
    }

    public String getTotalPositiveResultsWithDescription() {
        return statisticsText.get(1) + totalPositiveResults;
    }

    public String getTotalNegativeResultsWithDescription() {
        return statisticsText.get(2) + totalNegativeResults;
    }

    public String getMeasuredObjectsWithDescription() {
        return statisticsText.get(3) + measuredObjects;
    }

    public String getTotalOnePhaseCircuitsWithDescription() {
        return statisticsText.get(4) + totalOnePhaseCircuits;
    }

    public String getTotalThreePhaseCircuitsWithDescription() {
        return statisticsText.get(5) + totalThreePhaseCircuits;
    }

    public String getTotalNoneResultsWithDescription() {
        return statisticsText.get(6) + totalNoneResults;
    }

    private boolean isInsulationMeasurement(MeasurementMain measurement) {
         return measurement.getMeasurementName().contains(measurementsMainNames.get(1).substring(7));
    }
    private boolean isSoilMeasurement(MeasurementMain measurement) {
        return measurement.getMeasurementName().equals(measurementsMainNames.get(4));
    }
    private boolean isInsulationTnsMeasurement(String measurementName) {
        return measurementName.contains("TN-S");
    }
    private boolean isResult(MeasurementEntry entry, Result result) {
        return entry.getResult() == result;
    }
}
