package pl.koneckimarcin.electricalprotocolsmanager.measurement.continuityOfSmallResistancy.entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTnc.entry.CircuitInsulationResistanceTncEntry;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTnc.main.CircuitInsulationResistanceTnc;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.continuityOfSmallResistancy.main.ContinuityOfSmallResistance;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.continuityOfSmallResistancy.main.ContinuityOfSmallResistanceRepository;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.entry.MeasurementEntryController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/5")
public class ContinuityOfSmallResistanceEntryController
        implements MeasurementEntryController<ContinuityOfSmallResistanceEntry> {

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
    public void deleteEntryById(int id, int mainId) {

        Optional<ContinuityOfSmallResistance> main = mainRepository.findById(mainId);
        Optional<ContinuityOfSmallResistanceEntry> entry = entryRepository.findById(id);
        main.get().removeEntry(entry.get());

        entryRepository.deleteById(id);
    }

    @Override
    public void deleteAllEntries(int mainId) {

        Optional<ContinuityOfSmallResistance> main = mainRepository.findById(mainId);
        List<Integer> entriesToDelete = main.get().listEntriesId();
        main.get().removeAllEntries();

        for(Integer entryId : entriesToDelete){
            entryRepository.deleteById(entryId);
        }
    }
}
