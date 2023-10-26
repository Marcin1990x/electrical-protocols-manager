package pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTns.entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.entry.MeasurementEntryController;

import java.util.List;

@RestController
@RequestMapping("/1")
public class CircuitInsulationResistanceTnsEntryController
        implements MeasurementEntryController<CircuitInsulationResistanceTnsEntry> {

    @Autowired
    private CircuitInsulationResistanceTnsEntryRepository entryRepository;

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
}
