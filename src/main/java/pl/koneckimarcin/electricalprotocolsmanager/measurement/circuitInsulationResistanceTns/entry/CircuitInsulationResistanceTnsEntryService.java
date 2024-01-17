package pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTns.entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTns.main.CircuitInsulationResistanceTns;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTns.main.CircuitInsulationResistanceTnsRepository;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.entry.MeasurementEntryService;

import java.util.List;
import java.util.Optional;

@Service
public class CircuitInsulationResistanceTnsEntryService implements MeasurementEntryService<CircuitInsulationResistanceTnsEntry> {

    @Autowired
    private CircuitInsulationResistanceTnsEntryRepository entryRepository;
    @Autowired
    private CircuitInsulationResistanceTnsRepository mainRepository;

    @Override
    public List<CircuitInsulationResistanceTnsEntry> getEntries() {

        return entryRepository.findAll();
    }

    @Override
    public CircuitInsulationResistanceTnsEntry addEntry(CircuitInsulationResistanceTnsEntry entry) {

        entry.setResult();
        entryRepository.save(entry);
        return entry;
    }

    @Override
    public void deleteEntryById(int entryId, int mainId) {

        Optional<CircuitInsulationResistanceTns> main = mainRepository.findById(mainId);
        Optional<CircuitInsulationResistanceTnsEntry> entry = entryRepository.findById(entryId);
        main.get().removeEntry(entry.get());

        entryRepository.deleteById(entryId);
    }

    @Override
    public void deleteAllEntries(int mainId) {

        Optional<CircuitInsulationResistanceTns> main = mainRepository.findById(mainId);
        List<Integer> entriesToDelete = main.get().listEntriesId();
        main.get().removeAllEntries();

        for (Integer entryId : entriesToDelete) {
            entryRepository.deleteById(entryId);
        }
    }

    public CircuitInsulationResistanceTnsEntry updateEntry
            (int entryId, CircuitInsulationResistanceTnsEntry newEntry) {

        Optional<CircuitInsulationResistanceTnsEntry> entryToUpdate =
                entryRepository.findById(entryId);

        if (entryToUpdate.isPresent()) {
            entryToUpdate.get().setSymbol(newEntry.getSymbol());
            entryToUpdate.get().setCircuitName(newEntry.getCircuitName());
            entryToUpdate.get().setL1l2(newEntry.getL1l2());
            entryToUpdate.get().setL2l3(newEntry.getL2l3());
            entryToUpdate.get().setL3l1(newEntry.getL3l1());
            entryToUpdate.get().setL1pe(newEntry.getL1pe());
            entryToUpdate.get().setL2pe(newEntry.getL2pe());
            entryToUpdate.get().setL3pe(newEntry.getL3pe());
            entryToUpdate.get().setL1n(newEntry.getL1n());
            entryToUpdate.get().setL2n(newEntry.getL2n());
            entryToUpdate.get().setL3n(newEntry.getL3n());
            entryToUpdate.get().setNpe(newEntry.getNpe());
            entryToUpdate.get().setRa(newEntry.getRa());

            entryToUpdate.get().setResult();

            entryRepository.save(entryToUpdate.get());
        }
        return entryToUpdate.get();
    }
}
