package pl.koneckimarcin.electricalprotocolsmanager.measurement.protectionAgainstElectricShockByAutomaticShutdown.entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.entry.MeasurementEntryController;

import java.util.List;

@RestController
@RequestMapping("/0")
public class ProtectionMeasurementEntryDtoController
        implements MeasurementEntryController<ProtectionMeasurementEntryDto> {

    @Autowired
    private ProtectionMeasurementEntryDtoRepository entryRepository;

    @Override
    public List<ProtectionMeasurementEntryDto> getEntries() {

        return entryRepository.findAll();
    }

    @Override
    public ProtectionMeasurementEntryDto addEntry(ProtectionMeasurementEntryDto entry) {

        entry.setIa();
        entry.setZa();
        entry.setIk();
        entry.setResult();
        entryRepository.save(entry);
        return entry;
    }
}
