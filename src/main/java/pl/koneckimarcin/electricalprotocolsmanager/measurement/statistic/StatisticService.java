package pl.koneckimarcin.electricalprotocolsmanager.measurement.statistic;

import org.springframework.stereotype.Service;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.MeasurementMain;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.data.TextData;
import pl.koneckimarcin.electricalprotocolsmanager.structure.model.Building;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatisticService {

    public List<String> getMeasurementsStatistics(Building building) {

        List<String> measurementsStatisticTextData;

        List<MeasurementMain> measurementList = building.getMeasurementMainList();

        measurementsStatisticTextData =  getProtectionMeasurementStatisticTextData(measurementList);

        return measurementsStatisticTextData;
    }

    private List<String> getProtectionMeasurementStatisticTextData(List<MeasurementMain> measurements) {

        int totalMeasuringPoints = 0;
        int totalPositiveResults = 0;

        List<String> statisticsTextData = new ArrayList<>();

        for(MeasurementMain measurement : measurements) {
            totalMeasuringPoints += measurement.getStatistic().getTotalMeasuringPoints();
            totalPositiveResults += measurement.getStatistic().getTotalPositiveResults();
        }
        statisticsTextData.add(measurements.get(0).getMeasurementName());
        statisticsTextData.add(TextData.protectionMeasurementStatisticText.get(0) + totalMeasuringPoints);
        statisticsTextData.add(TextData.protectionMeasurementStatisticText.get(1) + totalPositiveResults);

        return statisticsTextData;
    }
}
