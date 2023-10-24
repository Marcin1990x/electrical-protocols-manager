package pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTns.entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.entry.MeasurementEntryController;

import java.util.List;

@RestController
@RequestMapping("/1")
public class CircuitInsulationResistanceTnsEntryDtoController
        implements MeasurementEntryController<CircuitInsulationResistanceTnsEntryDto> {

    @Autowired
    private CircuitInsulationResistanceTnsEntryDtoRepository entryRepository;

    @Override
    public List<CircuitInsulationResistanceTnsEntryDto> getEntries() {

        return entryRepository.findAll();
    }

    @Override
    public CircuitInsulationResistanceTnsEntryDto addEntry(CircuitInsulationResistanceTnsEntryDto entry) {

        entryRepository.save(entry);
        return entry;
    }
}
