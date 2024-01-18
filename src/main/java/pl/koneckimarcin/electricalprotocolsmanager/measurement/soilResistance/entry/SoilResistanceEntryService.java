package pl.koneckimarcin.electricalprotocolsmanager.measurement.soilResistance.entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.entry.MeasurementEntryService;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.soilResistance.main.SoilResistance;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.soilResistance.main.SoilResistanceRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SoilResistanceEntryService implements MeasurementEntryService<SoilResistanceEntry> {

    @Autowired
    private SoilResistanceEntryRepository entryRepository;
    @Autowired
    private SoilResistanceRepository mainRepository;

    @Override
    public List<SoilResistanceEntry> getEntries() {

        return entryRepository.findAll();
    }

    @Override
    public SoilResistanceEntry addEntry(SoilResistanceEntry entry) {

        entryRepository.save(entry);
        return entry;
    }

    @Override
    public void deleteEntryById(int entryId, int mainId) {

        Optional<SoilResistance> main = mainRepository.findById(mainId);
        Optional<SoilResistanceEntry> entry = entryRepository.findById(entryId);
        main.get().removeEntry(entry.get());

        entryRepository.deleteById(entryId);
    }

    @Override
    public void deleteAllEntries(int mainId) {

        Optional<SoilResistance> main = mainRepository.findById(mainId);
        List<Integer> entriesToDelete = main.get().listEntriesId();
        main.get().removeAllEntries();

        for (Integer entryId : entriesToDelete) {
            entryRepository.deleteById(entryId);
        }
    }

    @Override
    public SoilResistanceEntry updateEntry(int entryId, SoilResistanceEntry newEntry) {

        Optional<SoilResistanceEntry> entryToUpdate =
                entryRepository.findById(entryId);

        if (entryToUpdate.isPresent()) {
            entryToUpdate.get().setSymbol(newEntry.getSymbol());
            entryToUpdate.get().setMeasuringPoint(newEntry.getMeasuringPoint());
            entryToUpdate.get().setL(newEntry.getL());
            entryToUpdate.get().setD(newEntry.getD());
            entryToUpdate.get().setP(newEntry.getP());

            entryRepository.save(entryToUpdate.get());
        }
        return entryToUpdate.get();
    }
}
