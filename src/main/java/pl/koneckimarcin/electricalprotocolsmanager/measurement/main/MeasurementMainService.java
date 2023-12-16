package pl.koneckimarcin.electricalprotocolsmanager.measurement.main;

import java.util.List;

public interface MeasurementMainService<T> {

    public List<T> getMains();

    public T addMain(T t);

    public T addEntryToMain(int mainId, int entryId);

}
