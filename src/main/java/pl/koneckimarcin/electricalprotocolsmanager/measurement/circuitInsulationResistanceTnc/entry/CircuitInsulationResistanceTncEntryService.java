package pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTnc.entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTnc.main.CircuitInsulationResistanceTnc;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTnc.main.CircuitInsulationResistanceTncRepository;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.entry.MeasurementEntryService;

import java.util.List;
import java.util.Optional;

@Service
public class CircuitInsulationResistanceTncEntryService implements MeasurementEntryService<CircuitInsulationResistanceTncEntry> {

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

        for (Integer entryId : entriesToDelete) {
            entryRepository.deleteById(entryId);
        }
    }

    public CircuitInsulationResistanceTncEntry updateEntry
            (int entryId, CircuitInsulationResistanceTncEntry newEntry) {

        Optional<CircuitInsulationResistanceTncEntry> entryToUpdate =
                entryRepository.findById(entryId);

        if (entryToUpdate.isPresent()) {
            entryToUpdate.get().setSymbol(newEntry.getSymbol());
            entryToUpdate.get().setCircuitName(newEntry.getCircuitName());
            entryToUpdate.get().setL1l2(newEntry.getL1l2());
            entryToUpdate.get().setL2l3(newEntry.getL2l3());
            entryToUpdate.get().setL3l1(newEntry.getL3l1());
            entryToUpdate.get().setL1pen(newEntry.getL1pen());
            entryToUpdate.get().setL2pen(newEntry.getL2pen());
            entryToUpdate.get().setL3pen(newEntry.getL3pen());
            entryToUpdate.get().setRa(newEntry.getRa());

            entryToUpdate.get().setResult();

            entryRepository.save(entryToUpdate.get());
        }
        return entryToUpdate.get();
    }
}
