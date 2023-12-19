package pl.koneckimarcin.electricalprotocolsmanager.measurement.statistic;

import org.springframework.stereotype.Service;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.main.MeasurementMain;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.protocolTextData.TextsPL;
import pl.koneckimarcin.electricalprotocolsmanager.structure.building.Building;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static pl.koneckimarcin.electricalprotocolsmanager.measurement.protocolTextData.TextsPL.*;

@Service
public class StatisticService {

    public List<List<String>> buildMeasurementsStatistics(Building building) {

        List<List<String>> completeMeasurementsStatistics = new ArrayList<>();
        List<MeasurementMain> measurementList = building.getMeasurementMainList();
        List<String> distinctMeasurements = building.extractMeasurementMainDistinctNames();

        for (String measurement : distinctMeasurements) {

            if (areTextsEqual(measurement, TextsPL.measurementsMainNames.get(0))) {
                completeMeasurementsStatistics.add(getStatisticsForMeasurement(
                        TextsPL.measurementsMainNames.get(0), measurementList, protectionMeasurementStatisticText));
            } else if (areTextsEqual(measurement, TextsPL.measurementsMainNames.get(1))) {
                completeMeasurementsStatistics.add(getStatisticsForMeasurement(
                        TextsPL.measurementsMainNames.get(1), measurementList, circuitInsulationMeasurementStatisticText));
            } else if (areTextsEqual(measurement, TextsPL.measurementsMainNames.get(2))) {
                completeMeasurementsStatistics.add(getStatisticsForMeasurement(
                        TextsPL.measurementsMainNames.get(2), measurementList, circuitInsulationMeasurementStatisticText));
            } else if (areTextsEqual(measurement, TextsPL.measurementsMainNames.get(3))) {
                completeMeasurementsStatistics.add(getStatisticsForMeasurement(
                        TextsPL.measurementsMainNames.get(3), measurementList, commonStatisticText));
            } else if (areTextsEqual(measurement, TextsPL.measurementsMainNames.get(4))) {
                completeMeasurementsStatistics.add(getStatisticsForMeasurement(
                        TextsPL.measurementsMainNames.get(4), measurementList, commonStatisticText));
            } else if (areTextsEqual(measurement, TextsPL.measurementsMainNames.get(5))) {
                completeMeasurementsStatistics.add(getStatisticsForMeasurement(
                        TextsPL.measurementsMainNames.get(5), measurementList, commonStatisticText));
            } else {
                throw new IllegalArgumentException("No statistic creation method for this measurement main name.");
            }
        }
        return completeMeasurementsStatistics;
    }

    private boolean areTextsEqual(String text, String text2) {
        return text.equals(text2);
    }

    private List<String> getStatisticsForMeasurement(String measurementName, List<MeasurementMain> measurements,
                                                     List<String> translations) {

        List<MeasurementMain> list = extractMeasurementsByName(measurementName, measurements);
        return getMeasurementStatisticsTextData(list, translations);
    }

    private List<String> getMeasurementStatisticsTextData(List<MeasurementMain> measurements, List<String> statisticsTranslations) {

        Statistics statistics = new Statistics(measurements);

        List<String> measurementStatisticsTextData = getStatisticsTextList(statistics, measurements.get(0).getMeasurementName(),
                statisticsTranslations);

        return measurementStatisticsTextData;
    }

    private List<String> getStatisticsTextList(Statistics statistics, String measurementName, List<String> legendList) {

        List<String> statisticsTextList = new ArrayList<>();

        statisticsTextList.add(measurementName);

        if (measurementName.equals(TextsPL.measurementsMainNames.get(0))) {
            statisticsTextList.add(protectionMeasurementStatisticText.get(0) + statistics.totalMeasuringPoints);
            statisticsTextList.add(protectionMeasurementStatisticText.get(1) + statistics.totalPositiveResults);
            statisticsTextList.add(protectionMeasurementStatisticText.get(2) + statistics.measuredObjects);
        }
        if (measurementName.contains("Badanie rezystancji izolacji obwodów")) {
            statisticsTextList.add(TextsPL.circuitInsulationMeasurementStatisticText.get(0) + statistics.totalOnePhaseCircuits);
            statisticsTextList.add(TextsPL.circuitInsulationMeasurementStatisticText.get(1) + statistics.totalThreePhaseCircuits);
            statisticsTextList.add(TextsPL.circuitInsulationMeasurementStatisticText.get(2) + statistics.totalPositiveResults);
            statisticsTextList.add(TextsPL.circuitInsulationMeasurementStatisticText.get(3) + statistics.measuredObjects);
        }
        if (measurementName.equals(TextsPL.measurementsMainNames.get(3)) ||
                measurementName.equals(TextsPL.measurementsMainNames.get(5))) {
            statisticsTextList.add(TextsPL.commonStatisticText.get(0) + statistics.totalMeasuringPoints);
            statisticsTextList.add(TextsPL.commonStatisticText.get(1) + statistics.totalPositiveResults);
            statisticsTextList.add(TextsPL.commonStatisticText.get(2) + statistics.totalNegativeResults);
        }
        if (measurementName.equals(TextsPL.measurementsMainNames.get(4))) {
            statisticsTextList.add(TextsPL.soilResistanceStatisticText.get(0) + statistics.totalMeasuringPoints);
            statisticsTextList.add(TextsPL.soilResistanceStatisticText.get(1) + statistics.totalNoneResults);
        }
        return statisticsTextList;
    }

    private List<MeasurementMain> extractMeasurementsByName(String name, List<MeasurementMain> measurementMainList) {

        return measurementMainList
                .stream()
                .filter(measurementMain -> measurementMain.getMeasurementName().equals(name))
                .collect(Collectors.toList());
    }
}
