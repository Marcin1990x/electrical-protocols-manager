package pl.koneckimarcin.electricalprotocolsmanager.measurement.soilResistance.entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.entry.MeasurementEntryController;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.soilResistance.main.SoilResistance;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.soilResistance.main.SoilResistanceRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/4")
public class SoilResistanceEntryController
        implements MeasurementEntryController<SoilResistanceEntry> {

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
    public void deleteEntryById(int id, int mainId) {

        Optional<SoilResistance> main = mainRepository.findById(mainId);
        Optional<SoilResistanceEntry> entry = entryRepository.findById(id);
        main.get().removeEntry(entry.get());

        entryRepository.deleteById(id);
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
}
