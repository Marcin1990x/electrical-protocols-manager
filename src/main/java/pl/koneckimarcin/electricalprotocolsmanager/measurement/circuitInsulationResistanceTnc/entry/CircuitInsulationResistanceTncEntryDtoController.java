package pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTnc.entry;

import org.springframework.web.bind.annotation.RestController;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.entry.MeasurementEntryController;

import java.util.List;

@RestController
public class CircuitInsulationResistanceTncEntryDtoController
        implements MeasurementEntryController<CircuitInsulationResistanceTncEntryDto> {

    private CircuitInsulationResistanceTncEntryDtoRepository entryRepository;

    public CircuitInsulationResistanceTncEntryDtoController(CircuitInsulationResistanceTncEntryDtoRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    @Override
    public List<CircuitInsulationResistanceTncEntryDto> getEntries() {

        return entryRepository.findAll();
    }

    @Override
    public CircuitInsulationResistanceTncEntryDto addEntry(CircuitInsulationResistanceTncEntryDto entry) {

        entryRepository.save(entry);
        return entry;
    }
}
