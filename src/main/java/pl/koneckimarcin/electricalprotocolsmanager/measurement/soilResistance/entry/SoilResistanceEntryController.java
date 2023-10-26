package pl.koneckimarcin.electricalprotocolsmanager.measurement.soilResistance.entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.entry.MeasurementEntryController;

import java.util.List;

@RestController
@RequestMapping("/4")
public class SoilResistanceEntryController
        implements MeasurementEntryController<SoilResistanceEntry> {

    @Autowired
    private SoilResistanceEntryRepository entryRepository;

    @Override
    public List<SoilResistanceEntry> getEntries() {

        return entryRepository.findAll();
    }

    @Override
    public SoilResistanceEntry addEntry(SoilResistanceEntry entry) {

        entryRepository.save(entry);
        return entry;
    }
}
