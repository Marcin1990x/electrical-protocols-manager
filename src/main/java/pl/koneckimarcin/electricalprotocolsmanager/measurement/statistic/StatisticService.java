package pl.koneckimarcin.electricalprotocolsmanager.measurement.statistic;

import org.springframework.stereotype.Service;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.MeasurementEntry;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.MeasurementMain;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.Result;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.data.TextData;
import pl.koneckimarcin.electricalprotocolsmanager.structure.model.Building;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatisticService {

    public List<List<String>> getMeasurementsStatistics(Building building) {

        List<List<String>> measurementsStatisticTextDataLists = new ArrayList<>();

        List<MeasurementMain> measurementList = building.getMeasurementMainList();

        List<String> measurementsDistinct = measurementList
                .stream()
                .map(MeasurementMain::getMeasurementName)
                .distinct()
                .toList();

        for (String measurement : measurementsDistinct ) {
            if(measurement.equals(TextData.measurementsMainNames.get(0))) {
                List<MeasurementMain> list = measurementList
                        .stream()
                        .filter(measurementMain -> measurementMain.getMeasurementName()
                                .equals(TextData.measurementsMainNames.get(0))).toList();
                measurementsStatisticTextDataLists.add(getProtectionMeasurementStatisticTextData(list));
            } else {
                throw new IllegalArgumentException("No statistic creation method for this measurement main name.");
            }
        }
        return measurementsStatisticTextDataLists;
    }

    private List<String> getProtectionMeasurementStatisticTextData(List<MeasurementMain> measurements) {

        int totalMeasuringPoints = 0;
        int totalPositiveResults = 0;
        int measuredObjects = 0;

        List<String> statisticsTextData = new ArrayList<>();

        for(MeasurementMain measurement : measurements) {
            totalMeasuringPoints += measurement.getMeasurementEntries().size();
            for (MeasurementEntry entry : measurement.getMeasurementEntries()){
                if(entry.getResult() == Result.POSITIVE){
                    totalPositiveResults ++;
                }
            }
        }

        measuredObjects = measurements.size();

        statisticsTextData.add(measurements.get(0).getMeasurementName());
        statisticsTextData.add(TextData.protectionMeasurementStatisticText.get(0) + totalMeasuringPoints);
        statisticsTextData.add(TextData.protectionMeasurementStatisticText.get(1) + totalPositiveResults);
        statisticsTextData.add(TextData.protectionMeasurementStatisticText.get(2) + measuredObjects);

        return statisticsTextData;
    }
}
