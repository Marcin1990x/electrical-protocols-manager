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
                        TextsPL.measurementsMainNames.get(0), measurementList));
            } else if (areTextsEqual(measurement, TextsPL.measurementsMainNames.get(1))) {
                completeMeasurementsStatistics.add(getStatisticsForMeasurement(
                        TextsPL.measurementsMainNames.get(1), measurementList));
            } else if (areTextsEqual(measurement, TextsPL.measurementsMainNames.get(2))) {
                completeMeasurementsStatistics.add(getStatisticsForMeasurement(
                        TextsPL.measurementsMainNames.get(2), measurementList));
            } else if (areTextsEqual(measurement, TextsPL.measurementsMainNames.get(3))) {
                completeMeasurementsStatistics.add(getStatisticsForMeasurement(
                        TextsPL.measurementsMainNames.get(3), measurementList));
            } else if (areTextsEqual(measurement, TextsPL.measurementsMainNames.get(4))) {
                completeMeasurementsStatistics.add(getStatisticsForMeasurement(
                        TextsPL.measurementsMainNames.get(4), measurementList));
            } else if (areTextsEqual(measurement, TextsPL.measurementsMainNames.get(5))) {
                completeMeasurementsStatistics.add(getStatisticsForMeasurement(
                        TextsPL.measurementsMainNames.get(5), measurementList));
            } else {
                throw new IllegalArgumentException("No statistic creation method for this measurement main name.");
            }
        }
        return completeMeasurementsStatistics;
    }

    private boolean areTextsEqual(String text, String text2) {
        return text.equals(text2);
    }

    private List<String> getStatisticsForMeasurement(String measurementName, List<MeasurementMain> measurements) {

        List<MeasurementMain> list = extractMeasurementsByName(measurementName, measurements);
        return getMeasurementStatisticsTextData(list);
    }

    private List<String> getMeasurementStatisticsTextData(List<MeasurementMain> measurements) {

        Statistics statistics = new Statistics(measurements);

        List<String> measurementStatisticsTextData = getStatisticsTextList(statistics, measurements.get(0).getMeasurementName());

        return measurementStatisticsTextData;
    }

    private List<String> getStatisticsTextList(Statistics statistics, String measurementName) {

        List<String> statisticsTextList = new ArrayList<>();

        statisticsTextList.add(measurementName);

        if (areTextsEqual(measurementName, measurementsMainNames.get(0))) {
            statisticsTextList.add(statistics.getTotalMeasuringPointsWithDescription());
            statisticsTextList.add(statistics.getTotalPositiveResultsWithDescription());
            statisticsTextList.add(statistics.getMeasuredObjectsWithDescription());
        }
        if (measurementName.contains(measurementsMainNames.get(1).substring(7))) {

            statisticsTextList.add(statistics.getTotalOnePhaseCircuitsWithDescription());
            statisticsTextList.add(statistics.getTotalThreePhaseCircuitsWithDescription());
            statisticsTextList.add(statistics.getTotalPositiveResultsWithDescription());
            statisticsTextList.add(statistics.getMeasuredObjectsWithDescription());
        }
        if (areTextsEqual(measurementName, measurementsMainNames.get(3)) ||
                areTextsEqual(measurementName, measurementsMainNames.get(5))) {
            statisticsTextList.add(statistics.getTotalMeasuringPointsWithDescription());
            statisticsTextList.add(statistics.getTotalPositiveResultsWithDescription());
            statisticsTextList.add(statistics.getTotalNegativeResultsWithDescription());
        }
        if (areTextsEqual(measurementName, measurementsMainNames.get(4))) {
            statisticsTextList.add(statistics.getTotalMeasuringPointsWithDescription());
            statisticsTextList.add(statistics.getTotalNoneResultsWithDescription());
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
