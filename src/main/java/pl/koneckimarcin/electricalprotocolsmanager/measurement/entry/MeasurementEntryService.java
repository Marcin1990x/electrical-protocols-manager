package pl.koneckimarcin.electricalprotocolsmanager.measurement.entry;

import pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTns.entry.CircuitInsulationResistanceTnsEntry;

import java.util.List;

public interface MeasurementEntryService<T> {

    public List<T> getEntries();

    public T addEntry(T t);

    public void deleteEntryById(int entryId, int mainId);

    public void deleteAllEntries(int mainId);

    public T updateEntry
            (int entryId, T newEntry);
}
