package pl.koneckimarcin.electricalprotocolsmanager.measurement.protectionAgainstElectricShockByAutomaticShutdown.entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.entry.MeasurementEntryService;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.protectionAgainstElectricShockByAutomaticShutdown.main.ProtectionAgainstElectricShockByAutomaticShutdown;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.protectionAgainstElectricShockByAutomaticShutdown.main.ProtectionAgainstElectricShockByAutomaticShutdownRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProtectionMeasurementEntryService implements MeasurementEntryService <ProtectionMeasurementEntry> {

    @Autowired
    private ProtectionMeasurementEntryRepository entryRepository;
    @Autowired
    private ProtectionAgainstElectricShockByAutomaticShutdownRepository mainRepository;

    @Override
    public List<ProtectionMeasurementEntry> getEntries() {

        return entryRepository.findAll();
    }

    @Override
    public ProtectionMeasurementEntry addEntry(ProtectionMeasurementEntry entry) {

        entry.setIa();
        entry.setZa();
        entry.setIk();
        entry.setResult();
        entryRepository.save(entry);
        return entry;
    }

    @Override
    public void deleteEntryById(int entryId, int mainId) {

        Optional<ProtectionAgainstElectricShockByAutomaticShutdown> main = mainRepository.findById(mainId);
        Optional<ProtectionMeasurementEntry> entry = entryRepository.findById(entryId);
        main.get().removeEntry(entry.get());

        entryRepository.deleteById(entryId);
    }

    @Override
    public void deleteAllEntries(int mainId) {

        Optional<ProtectionAgainstElectricShockByAutomaticShutdown> main = mainRepository.findById(mainId);
        List<Integer> entriesToDelete = main.get().listEntriesId();
        main.get().removeAllEntries();

        for (Integer entryId : entriesToDelete) {
            entryRepository.deleteById(entryId);
        }
    }

    public ProtectionMeasurementEntry updateEntry(int entryId, ProtectionMeasurementEntry newEntry) {

        Optional<ProtectionMeasurementEntry> entryToUpdate =
                entryRepository.findById(entryId);

        if (entryToUpdate.isPresent()) {
            entryToUpdate.get().setSymbol(newEntry.getSymbol());
            entryToUpdate.get().setMeasuringPoint(newEntry.getMeasuringPoint());
            entryToUpdate.get().setCutout(newEntry.getCutout());
            entryToUpdate.get().setType(newEntry.getType());
            entryToUpdate.get().setiNom(newEntry.getiNom());
            entryToUpdate.get().setZs(newEntry.getZs());

            entryToUpdate.get().setIa();
            entryToUpdate.get().setZa();
            entryToUpdate.get().setIk();
            entryToUpdate.get().setResult();

            entryRepository.save(entryToUpdate.get());
        }
        return entryToUpdate.get();
    }
}
