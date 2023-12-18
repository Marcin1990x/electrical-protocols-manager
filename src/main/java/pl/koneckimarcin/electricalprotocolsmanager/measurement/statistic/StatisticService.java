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

import static pl.koneckimarcin.electricalprotocolsmanager.measurement.protocolTextData.TextsPL.*;

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
                        .add(getMeasurementStatisticsTextData(list, protectionMeasurementStatisticText));
            } else if (areTextsEqual(measurement, TextsPL.measurementsMainNames.get(1))) {
                List<MeasurementMain> list =
                        extractMeasurementMainListByName(TextsPL.measurementsMainNames.get(1), measurementList);
                measurementsStatisticTextDataLists
                        .add(getMeasurementStatisticsTextData(list, circuitInsulationMeasurementStatisticText));
            } else if (areTextsEqual(measurement, TextsPL.measurementsMainNames.get(2))) {
                List<MeasurementMain> list =
                        extractMeasurementMainListByName(TextsPL.measurementsMainNames.get(2), measurementList);
                measurementsStatisticTextDataLists
                        .add(getMeasurementStatisticsTextData(list, circuitInsulationMeasurementStatisticText));
            } else if (areTextsEqual(measurement, TextsPL.measurementsMainNames.get(3))) {
                List<MeasurementMain> list =
                        extractMeasurementMainListByName(TextsPL.measurementsMainNames.get(3), measurementList);
                measurementsStatisticTextDataLists
                        .add(getMeasurementStatisticsTextData(list, commonStatisticText));
            } else if (areTextsEqual(measurement, TextsPL.measurementsMainNames.get(4))) {
                List<MeasurementMain> list =
                        extractMeasurementMainListByName(TextsPL.measurementsMainNames.get(4), measurementList);
                measurementsStatisticTextDataLists
                        .add(getMeasurementStatisticsTextData(list, commonStatisticText));
            } else if (areTextsEqual(measurement, TextsPL.measurementsMainNames.get(5))) {
                List<MeasurementMain> list =
                        extractMeasurementMainListByName(TextsPL.measurementsMainNames.get(5), measurementList);
                measurementsStatisticTextDataLists
                        .add(getMeasurementStatisticsTextData(list, commonStatisticText));
            } else {
                throw new IllegalArgumentException("No statistic creation method for this measurement main name.");
            }
        }
        return measurementsStatisticTextDataLists;
    }
    private boolean areTextsEqual(String text, String text2) {
        return text.equals(text2);
    }
    private List<String> getMeasurementStatisticsTextData(List<MeasurementMain> measurements, List<String> statisticsTranslations) {

        Statistics statistics = getStatisticsValues(measurements);
        statistics.countNegativeResults();

        List<String> measurementStatisticsTextData = getStatisticsTextList(statistics, measurements.get(0).getMeasurementName(),
                statisticsTranslations);

        return measurementStatisticsTextData;
    }
    private Statistics getStatisticsValues(List<MeasurementMain> measurements) {

        Statistics statistics = new Statistics();

        for (MeasurementMain measurement : measurements) {
            statistics.totalMeasuringPoints += countMeasuringPoints(measurement);
            statistics.totalPositiveResults += countPositiveResults(measurement);
            if(measurement.getMeasurementName().contains("Badanie rezystancji izolacji obwodów")) {
                statistics.totalOnePhaseCircuits += countOneAndThreePhaseCircuits(measurement)[0];
                statistics.totalThreePhaseCircuits += countOneAndThreePhaseCircuits(measurement)[1];
            }
            if(measurement.getMeasurementName().contains("gruntu")){
                statistics.totalNoneResults += countNoneResults(measurement);
            }
        }
        statistics.measuredObjects = measurements.size();

        return statistics;
    }
    private List<String> getStatisticsTextList(Statistics statistics, String measurementName, List<String> legendList) {

        List<String> statisticsTextList = new ArrayList<>();

        statisticsTextList.add(measurementName);

        if(measurementName.equals(TextsPL.measurementsMainNames.get(0)))
        {
            statisticsTextList.add(protectionMeasurementStatisticText.get(0) + statistics.totalMeasuringPoints);
            statisticsTextList.add(protectionMeasurementStatisticText.get(1) + statistics.totalPositiveResults);
            statisticsTextList.add(protectionMeasurementStatisticText.get(2) + statistics.measuredObjects);
        }
        if(measurementName.contains("Badanie rezystancji izolacji obwodów")) {
            statisticsTextList.add(TextsPL.circuitInsulationMeasurementStatisticText.get(0) + statistics.totalOnePhaseCircuits);
            statisticsTextList.add(TextsPL.circuitInsulationMeasurementStatisticText.get(1) + statistics.totalThreePhaseCircuits);
            statisticsTextList.add(TextsPL.circuitInsulationMeasurementStatisticText.get(2) + statistics.totalPositiveResults);
            statisticsTextList.add(TextsPL.circuitInsulationMeasurementStatisticText.get(3) + statistics.measuredObjects);
        }
        if(measurementName.equals(TextsPL.measurementsMainNames.get(3)) ||
                measurementName.equals(TextsPL.measurementsMainNames.get(5))) {
            statisticsTextList.add(TextsPL.commonStatisticText.get(0) + statistics.totalMeasuringPoints);
            statisticsTextList.add(TextsPL.commonStatisticText.get(1) + statistics.totalPositiveResults);
            statisticsTextList.add(TextsPL.commonStatisticText.get(2) + statistics.totalNegativeResults);
        }
        if(measurementName.equals(TextsPL.measurementsMainNames.get(4)))
        {
            statisticsTextList.add(TextsPL.soilResistanceStatisticText.get(0) + statistics.totalMeasuringPoints);
            statisticsTextList.add(TextsPL.soilResistanceStatisticText.get(1) + statistics.totalNoneResults);
        }
        return statisticsTextList;
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
    private List<MeasurementMain> extractMeasurementMainListByName(String name, List<MeasurementMain> measurementMainList) {

        return measurementMainList.stream()
                .filter(measurementMain -> measurementMain.getMeasurementName().equals(name))
                .collect(Collectors.toList());
    }
}
