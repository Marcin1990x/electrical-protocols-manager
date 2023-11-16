package pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTns.entry;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTnc.entry.CircuitInsulationResistanceTncEntry;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTnc.main.CircuitInsulationResistanceTnc;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTns.main.CircuitInsulationResistanceTns;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTns.main.CircuitInsulationResistanceTnsRepository;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.entry.MeasurementEntryController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/1")
public class CircuitInsulationResistanceTnsEntryController
        implements MeasurementEntryController<CircuitInsulationResistanceTnsEntry> {

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
    public void deleteEntryById(int id, int mainId) {

        Optional<CircuitInsulationResistanceTns> main = mainRepository.findById(mainId);
        Optional<CircuitInsulationResistanceTnsEntry> entry = entryRepository.findById(id);
        main.get().removeEntry(entry.get());

        entryRepository.deleteById(id);
    }

    @Override
    public void deleteAllEntries(int mainId) {

        Optional<CircuitInsulationResistanceTns> main = mainRepository.findById(mainId);
        List<Integer> entriesToDelete = main.get().listEntriesId();
        main.get().removeAllEntries();

        for(Integer entryId : entriesToDelete){
            entryRepository.deleteById(entryId);
        }
    }
}
