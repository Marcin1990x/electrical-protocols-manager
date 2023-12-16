package pl.koneckimarcin.electricalprotocolsmanager.measurement.entry;

import java.util.List;

public interface MeasurementEntryService<T> {

    public List<T> getEntries();

    public T addEntry(T t);

    public void deleteEntryById(int entryId, int mainId);

    public void deleteAllEntries(int mainId);
}
