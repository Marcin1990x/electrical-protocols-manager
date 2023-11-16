package pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTnc.entry;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTnc.main.CircuitInsulationResistanceTnc;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTnc.main.CircuitInsulationResistanceTncRepository;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.entry.MeasurementEntryController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/2")
public class CircuitInsulationResistanceTncEntryController
        implements MeasurementEntryController<CircuitInsulationResistanceTncEntry> {

    private CircuitInsulationResistanceTncEntryRepository entryRepository;
    private CircuitInsulationResistanceTncRepository mainRepository;

    public CircuitInsulationResistanceTncEntryController(CircuitInsulationResistanceTncEntryRepository entryRepository,
                                                         CircuitInsulationResistanceTncRepository mainRepository) {
        this.entryRepository = entryRepository;
        this.mainRepository = mainRepository;
    }

    @Override
    public List<CircuitInsulationResistanceTncEntry> getEntries() {

        return entryRepository.findAll();
    }

    @Override
    public CircuitInsulationResistanceTncEntry addEntry(CircuitInsulationResistanceTncEntry entry) {

        entry.setResult();
        entryRepository.save(entry);
        return entry;
    }

    @Override
    public void deleteEntryById(int id, int mainId) {

        Optional<CircuitInsulationResistanceTnc> main = mainRepository.findById(mainId);
        Optional<CircuitInsulationResistanceTncEntry> entry = entryRepository.findById(id);
        main.get().removeEntry(entry.get());

        entryRepository.deleteById(id);
    }

    @Override
    public void deleteAllEntries(int mainId) {

        Optional<CircuitInsulationResistanceTnc> main = mainRepository.findById(mainId);
        List<Integer> entriesToDelete = main.get().listEntriesId();
        main.get().removeAllEntries();

        for(Integer entryId : entriesToDelete){
            entryRepository.deleteById(entryId);
        }
    }
}
