package pl.koneckimarcin.electricalprotocolsmanager.measurement.soilResistance.entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.entry.MeasurementEntryController;

import java.util.List;

@RestController
@RequestMapping("/4")
public class SoilResistanceEntryDtoController
        implements MeasurementEntryController<SoilResistanceEntryDto> {

    @Autowired
    private SoilResistanceEntryDtoRepository entryRepository;

    @Override
    public List<SoilResistanceEntryDto> getEntries() {

        return entryRepository.findAll();
    }

    @Override
    public SoilResistanceEntryDto addEntry(SoilResistanceEntryDto entry) {

        entryRepository.save(entry);
        return entry;
    }
}
