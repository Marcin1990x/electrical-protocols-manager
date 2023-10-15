package pl.koneckimarcin.electricalprotocolsmanager.measurement.statistic;

import org.springframework.stereotype.Service;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.MeasurementEntry;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.MeasurementMain;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.Result;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTns.CircuitInsulationResistanceTnsEntry;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.data.TextData;
import pl.koneckimarcin.electricalprotocolsmanager.structure.model.Building;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatisticService {

    public List<List<String>> getMeasurementsStatistics(Building building) {

        List<List<String>> measurementsStatisticTextDataLists = new ArrayList<>();

        List<MeasurementMain> measurementList = building.getMeasurementMainList();

        List<String> distinctMeasurementMainNames = extractDistinctNames(measurementList);

        for (String measurement : distinctMeasurementMainNames ) {

            if(measurement.equals(TextData.measurementsMainNames.get(0))) {
                List<MeasurementMain> list =
                        extractMeasurementMainByName(TextData.measurementsMainNames.get(0), measurementList);
                measurementsStatisticTextDataLists
                        .add(getProtectionMeasurementStatisticTextData(list));
            } else if(measurement.equals(TextData.measurementsMainNames.get(1))) {
                List<MeasurementMain> list =
                        extractMeasurementMainByName(TextData.measurementsMainNames.get(1), measurementList);
                measurementsStatisticTextDataLists
                        .add(getCircuitInsulationTnsMeasurementStatisticTextData(list));
            }
            else {
                throw new IllegalArgumentException("No statistic creation method for this measurement main name.");
            }
        }
        return measurementsStatisticTextDataLists;
    }

    private List<String> getProtectionMeasurementStatisticTextData(List<MeasurementMain> measurements) {

        int totalMeasuringPoints = 0;
        int totalPositiveResults = 0;
        int measuredObjects;

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
    private List<String> getCircuitInsulationTnsMeasurementStatisticTextData(List<MeasurementMain> measurements) {

        int totalOnePhaseCircuits = 0;
        int totalThreePhaseCircuits = 0;
        int totalPositiveResults = 0;
        int measuredObjects;

        List<String> statisticsTextData = new ArrayList<>();
        for(MeasurementMain measurement : measurements) {
            for (MeasurementEntry entry : measurement.getMeasurementEntries()){
                if(entry.getResult() == Result.POSITIVE){
                    totalPositiveResults ++;
                }
                if(((CircuitInsulationResistanceTnsEntry)entry).getL1l2() == 0 ||
                        ((CircuitInsulationResistanceTnsEntry)entry).getL2l3() == 0 ||
                        ((CircuitInsulationResistanceTnsEntry)entry).getL3l1() == 0
                ) {
                    totalOnePhaseCircuits++;
                } else {
                    totalThreePhaseCircuits++;
                }
            }
        }
        measuredObjects = measurements.size();

        statisticsTextData.add(measurements.get(0).getMeasurementName());
        statisticsTextData.add(TextData.circuitInsulationTnsMeasurementStatisticText.get(0) + totalOnePhaseCircuits);
        statisticsTextData.add(TextData.circuitInsulationTnsMeasurementStatisticText.get(1) + totalThreePhaseCircuits);
        statisticsTextData.add(TextData.circuitInsulationTnsMeasurementStatisticText.get(2) + totalPositiveResults);
        statisticsTextData.add(TextData.circuitInsulationTnsMeasurementStatisticText.get(3) + measuredObjects);

        return statisticsTextData;
    }

    private List<String> extractDistinctNames(List<MeasurementMain> measurementMainList) {

        return measurementMainList.stream()
                .map(MeasurementMain::getMeasurementName)
                .distinct()
                .toList();
    }
    private List<MeasurementMain> extractMeasurementMainByName(String name, List<MeasurementMain> measurementMainList) {

        return measurementMainList.stream()
                .filter(measurementMain -> measurementMain.getMeasurementName().equals(name))
                .collect(Collectors.toList());
    }
}
