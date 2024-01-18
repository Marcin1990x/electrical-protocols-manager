package pl.koneckimarcin.electricalprotocolsmanager.measurement.continuityOfSmallResistancy.entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.continuityOfSmallResistancy.main.ContinuityOfSmallResistance;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.continuityOfSmallResistancy.main.ContinuityOfSmallResistanceRepository;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.entry.MeasurementEntryService;

import java.util.List;
import java.util.Optional;

@Service
public class ContinuityOfSmallResistanceEntryService implements MeasurementEntryService<ContinuityOfSmallResistanceEntry> {

    @Autowired
    private ContinuityOfSmallResistanceEntryRepository entryRepository;
    @Autowired
    private ContinuityOfSmallResistanceRepository mainRepository;

    @Override
    public List<ContinuityOfSmallResistanceEntry> getEntries() {

        return entryRepository.findAll();
    }

    @Override
    public ContinuityOfSmallResistanceEntry addEntry(ContinuityOfSmallResistanceEntry entry) {

        entry.setResult();
        entryRepository.save(entry);
        return entry;
    }

    @Override
    public void deleteEntryById(int entryId, int mainId) {

        Optional<ContinuityOfSmallResistance> main = mainRepository.findById(mainId);
        Optional<ContinuityOfSmallResistanceEntry> entry = entryRepository.findById(entryId);
        main.get().removeEntry(entry.get());

        entryRepository.deleteById(entryId);

    }

    @Override
    public void deleteAllEntries(int mainId) {

        Optional<ContinuityOfSmallResistance> main = mainRepository.findById(mainId);
        List<Integer> entriesToDelete = main.get().listEntriesId();
        main.get().removeAllEntries();

        for (Integer entryId : entriesToDelete) {
            entryRepository.deleteById(entryId);
        }
    }

    @Override
    public ContinuityOfSmallResistanceEntry updateEntry(int entryId, ContinuityOfSmallResistanceEntry newEntry) {

        Optional<ContinuityOfSmallResistanceEntry> entryToUpdate =
                entryRepository.findById(entryId);

        if (entryToUpdate.isPresent()) {
            entryToUpdate.get().setSymbol(newEntry.getSymbol());
            entryToUpdate.get().setMeasuringPoint(newEntry.getMeasuringPoint());
            entryToUpdate.get().setContinuity(newEntry.getContinuity());
            entryToUpdate.get().setRs(newEntry.getRs());
            entryToUpdate.get().setRa(newEntry.getRa());

            entryToUpdate.get().setResult();

            entryRepository.save(entryToUpdate.get());
        }
        return entryToUpdate.get();
    }
}
