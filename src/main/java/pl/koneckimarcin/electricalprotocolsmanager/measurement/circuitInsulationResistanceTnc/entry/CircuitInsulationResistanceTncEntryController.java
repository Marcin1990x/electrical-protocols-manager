package pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTnc.entry;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.entry.MeasurementEntryController;

import java.util.List;

@RestController
@RequestMapping("/2")
public class CircuitInsulationResistanceTncEntryController
        implements MeasurementEntryController<CircuitInsulationResistanceTncEntry> {

    private CircuitInsulationResistanceTncEntryDtoRepository entryRepository;

    public CircuitInsulationResistanceTncEntryController(CircuitInsulationResistanceTncEntryDtoRepository entryRepository) {
        this.entryRepository = entryRepository;
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
}
