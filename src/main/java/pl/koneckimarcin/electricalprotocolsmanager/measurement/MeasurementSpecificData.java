package pl.koneckimarcin.electricalprotocolsmanager.measurement;

import java.util.List;

public interface MeasurementSpecificData {

    public int[] getTableCellsSizes();

    public List<String> getMeasurementLegend();

    public List<String> getMeasurementTheoryDirectory();

    public List<Object> getMeasurementEntryTableHeaders();
}
