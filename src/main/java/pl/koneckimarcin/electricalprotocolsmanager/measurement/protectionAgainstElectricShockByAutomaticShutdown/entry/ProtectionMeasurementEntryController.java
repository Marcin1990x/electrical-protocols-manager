package pl.koneckimarcin.electricalprotocolsmanager.measurement.protectionAgainstElectricShockByAutomaticShutdown.entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.entry.MeasurementEntryController;

import java.util.List;

@RestController
@RequestMapping("/0")
public class ProtectionMeasurementEntryController
        implements MeasurementEntryController<ProtectionMeasurementEntry> {

    @Autowired
    private ProtectionMeasurementEntryRepository entryRepository;

    @Override
    public List<ProtectionMeasurementEntry> getEntries() {

        return entryRepository.findAll();
    }

    @Override
    public ProtectionMeasurementEntry addEntry(ProtectionMeasurementEntry entry) {

        entry.setIa();
        entry.setZa();
        entry.setIk();
        entry.setResult();
        entryRepository.save(entry);
        return entry;
    }
}
