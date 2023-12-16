package pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTnc.entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTnc.main.CircuitInsulationResistanceTnc;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTnc.main.CircuitInsulationResistanceTncRepository;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.entry.MeasurementEntryService;

import java.util.List;
import java.util.Optional;

@Service
public class CircuitInsulationResistanceTncEntryService implements MeasurementEntryService <CircuitInsulationResistanceTncEntry> {

    @Autowired
    private CircuitInsulationResistanceTncEntryRepository entryRepository;
    @Autowired
    private CircuitInsulationResistanceTncRepository mainRepository;

    public List<CircuitInsulationResistanceTncEntry> getEntries() {

        return entryRepository.findAll();
    }

    public CircuitInsulationResistanceTncEntry addEntry(CircuitInsulationResistanceTncEntry entry) {

        entry.setResult();
        entryRepository.save(entry);
        return entry;
    }

    public void deleteEntryById(int entryId, int mainId) {

        Optional<CircuitInsulationResistanceTnc> main = mainRepository.findById(mainId);
        Optional<CircuitInsulationResistanceTncEntry> entry = entryRepository.findById(entryId);
        main.get().removeEntry(entry.get());

        entryRepository.deleteById(entryId);
    }

    public void deleteAllEntries(int mainId) {

        Optional<CircuitInsulationResistanceTnc> main = mainRepository.findById(mainId);
        List<Integer> entriesToDelete = main.get().listEntriesId();
        main.get().removeAllEntries();

        for(Integer entryId : entriesToDelete){
            entryRepository.deleteById(entryId);
        }
    }
}
