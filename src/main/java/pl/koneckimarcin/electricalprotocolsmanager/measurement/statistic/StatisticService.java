package pl.koneckimarcin.electricalprotocolsmanager.measurement.statistic;

import org.springframework.stereotype.Service;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.Result;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTnc.entry.CircuitInsulationResistanceTncEntry;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTns.entry.CircuitInsulationResistanceTnsEntry;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.protocolTextData.TextsPL;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.entry.MeasurementEntry;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.main.MeasurementMain;
import pl.koneckimarcin.electricalprotocolsmanager.structure.building.Building;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatisticService {

    public List<List<String>> getMeasurementsStatistics(Building building) {

        List<List<String>> measurementsStatisticTextDataLists = new ArrayList<>();

        List<MeasurementMain> measurementList = building.getMeasurementMainList();

        List<String> distinctMeasurementMainNames = building.extractMeasurementMainDistinctNames();

        for (String measurement : distinctMeasurementMainNames) {

            if (areTextsEqual(measurement, TextsPL.measurementsMainNames.get(0))) {
                List<MeasurementMain> list =
                        extractMeasurementMainListByName(TextsPL.measurementsMainNames.get(0), measurementList);
                measurementsStatisticTextDataLists
                        .add(getProtectionMeasurementStatisticTextData(list));
            } else if (areTextsEqual(measurement, TextsPL.measurementsMainNames.get(1))) {
                List<MeasurementMain> list =
                        extractMeasurementMainListByName(TextsPL.measurementsMainNames.get(1), measurementList);
                measurementsStatisticTextDataLists
                        .add(getCircuitInsulationTnsMeasurementStatisticTextData(list));
            } else if (areTextsEqual(measurement, TextsPL.measurementsMainNames.get(2))) {
                List<MeasurementMain> list =
                        extractMeasurementMainListByName(TextsPL.measurementsMainNames.get(2), measurementList);
                measurementsStatisticTextDataLists
                        .add(getCircuitInsulationTncMeasurementStatisticTextData(list));
            } else if (areTextsEqual(measurement, TextsPL.measurementsMainNames.get(3))) {
                List<MeasurementMain> list =
                        extractMeasurementMainListByName(TextsPL.measurementsMainNames.get(3), measurementList);
                measurementsStatisticTextDataLists
                        .add(getCommonStatisticTextData(list));
            } else if (areTextsEqual(measurement, TextsPL.measurementsMainNames.get(4))) {
                List<MeasurementMain> list =
                        extractMeasurementMainListByName(TextsPL.measurementsMainNames.get(4), measurementList);
                measurementsStatisticTextDataLists
                        .add(getSoilResistanceStatisticTextData(list));
            } else if (areTextsEqual(measurement, TextsPL.measurementsMainNames.get(5))) {
                List<MeasurementMain> list =
                        extractMeasurementMainListByName(TextsPL.measurementsMainNames.get(5), measurementList);
                measurementsStatisticTextDataLists
                        .add(getCommonStatisticTextData(list));
            } else {
                throw new IllegalArgumentException("No statistic creation method for this measurement main name.");
            }
        }
        return measurementsStatisticTextDataLists;
    }

    private boolean areTextsEqual(String text, String text2) {
        return text.equals(text2);
    }

    private List<String> getProtectionMeasurementStatisticTextData(List<MeasurementMain> measurements) {

        int totalMeasuringPoints = 0;
        int totalPositiveResults = 0;
        int measuredObjects;

        List<String> statisticsTextData = new ArrayList<>();

        for (MeasurementMain measurement : measurements) {
            totalMeasuringPoints += countMeasuringPoints(measurement);
            totalPositiveResults += countPositiveResults(measurement);
        }

        measuredObjects = measurements.size();

        statisticsTextData.add(measurements.get(0).getMeasurementName());
        statisticsTextData.add(TextsPL.protectionMeasurementStatisticText.get(0) + totalMeasuringPoints);
        statisticsTextData.add(TextsPL.protectionMeasurementStatisticText.get(1) + totalPositiveResults);
        statisticsTextData.add(TextsPL.protectionMeasurementStatisticText.get(2) + measuredObjects);

        return statisticsTextData;
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

    // almost identical methods - fix !
    private List<String> getCircuitInsulationTnsMeasurementStatisticTextData(List<MeasurementMain> measurements) {

        int totalOnePhaseCircuits = 0;
        int totalThreePhaseCircuits = 0;
        int totalPositiveResults = 0;
        int measuredObjects;

        List<String> statisticsTextData = new ArrayList<>();
        for (MeasurementMain measurement : measurements) {
            for (MeasurementEntry entry : measurement.getMeasurementEntries()) {
                if (entry.getResult() == Result.POSITIVE) {
                    totalPositiveResults++;
                }
                if (((CircuitInsulationResistanceTnsEntry) entry).getL1l2() == 0 ||
                        ((CircuitInsulationResistanceTnsEntry) entry).getL2l3() == 0 ||
                        ((CircuitInsulationResistanceTnsEntry) entry).getL3l1() == 0
                ) {
                    totalOnePhaseCircuits++;
                } else {
                    totalThreePhaseCircuits++;
                }
            }
        }
        measuredObjects = measurements.size();

        statisticsTextData.add(measurements.get(0).getMeasurementName());
        statisticsTextData.add(TextsPL.circuitInsulationMeasurementStatisticText.get(0) + totalOnePhaseCircuits);
        statisticsTextData.add(TextsPL.circuitInsulationMeasurementStatisticText.get(1) + totalThreePhaseCircuits);
        statisticsTextData.add(TextsPL.circuitInsulationMeasurementStatisticText.get(2) + totalPositiveResults);
        statisticsTextData.add(TextsPL.circuitInsulationMeasurementStatisticText.get(3) + measuredObjects);

        return statisticsTextData;
    }

    // almost identical methods - fix !
    private List<String> getCircuitInsulationTncMeasurementStatisticTextData(List<MeasurementMain> measurements) {

        int totalOnePhaseCircuits = 0;
        int totalThreePhaseCircuits = 0;
        int totalPositiveResults = 0;
        int measuredObjects;

        List<String> statisticsTextData = new ArrayList<>();
        for (MeasurementMain measurement : measurements) {
            for (MeasurementEntry entry : measurement.getMeasurementEntries()) {
                if (entry.getResult() == Result.POSITIVE) {
                    totalPositiveResults++;
                }
                if (((CircuitInsulationResistanceTncEntry) entry).getL1l2() == 0 ||
                        ((CircuitInsulationResistanceTncEntry) entry).getL2l3() == 0 ||
                        ((CircuitInsulationResistanceTncEntry) entry).getL3l1() == 0
                ) {
                    totalOnePhaseCircuits++;
                } else {
                    totalThreePhaseCircuits++;
                }
            }
        }
        measuredObjects = measurements.size();

        statisticsTextData.add(measurements.get(0).getMeasurementName());
        statisticsTextData.add(TextsPL.circuitInsulationMeasurementStatisticText.get(0) + totalOnePhaseCircuits);
        statisticsTextData.add(TextsPL.circuitInsulationMeasurementStatisticText.get(1) + totalThreePhaseCircuits);
        statisticsTextData.add(TextsPL.circuitInsulationMeasurementStatisticText.get(2) + totalPositiveResults);
        statisticsTextData.add(TextsPL.circuitInsulationMeasurementStatisticText.get(3) + measuredObjects);

        return statisticsTextData;
    }

    private List<String> getCommonStatisticTextData(List<MeasurementMain> measurements) {

        int measurementPoints = 0;
        int totalPositiveResults = 0;

        List<String> statisticsTextData = new ArrayList<>();

        for (MeasurementMain measurement : measurements) {
            for (MeasurementEntry entry : measurement.getMeasurementEntries()) {
                measurementPoints++;
                if (entry.getResult() == Result.POSITIVE) {
                    totalPositiveResults++;
                }
            }
        }
        statisticsTextData.add(measurements.get(0).getMeasurementName());
        statisticsTextData.add(TextsPL.commonStatisticText.get(0) + measurementPoints);
        statisticsTextData.add(TextsPL.commonStatisticText.get(1) + totalPositiveResults);
        statisticsTextData.add(TextsPL.commonStatisticText.get(2)
                + (measurementPoints - totalPositiveResults));

        return statisticsTextData;
    }

    private List<String> getSoilResistanceStatisticTextData(List<MeasurementMain> measurements) {

        int measurementPoints = 0;
        int totalNoneResults = 0;

        List<String> statisticsTextData = new ArrayList<>();

        for (MeasurementMain measurement : measurements) {
            for (MeasurementEntry entry : measurement.getMeasurementEntries()) {
                measurementPoints++;
                if (entry.getResult() == Result.NONE) {
                    totalNoneResults++;
                }
            }
        }
        statisticsTextData.add(measurements.get(0).getMeasurementName());
        statisticsTextData.add(TextsPL.soilResistanceStatisticText.get(0) + measurementPoints);
        statisticsTextData.add(TextsPL.soilResistanceStatisticText.get(1) + totalNoneResults);

        return statisticsTextData;
    }

    private List<MeasurementMain> extractMeasurementMainListByName(String name, List<MeasurementMain> measurementMainList) {

        return measurementMainList.stream()
                .filter(measurementMain -> measurementMain.getMeasurementName().equals(name))
                .collect(Collectors.toList());
    }
}
