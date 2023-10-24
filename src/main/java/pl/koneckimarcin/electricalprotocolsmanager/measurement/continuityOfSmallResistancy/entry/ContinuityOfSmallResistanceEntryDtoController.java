package pl.koneckimarcin.electricalprotocolsmanager.measurement.continuityOfSmallResistancy.entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.entry.MeasurementEntryController;

import java.util.List;

@RestController
@RequestMapping("/5")
public class ContinuityOfSmallResistanceEntryDtoController
        implements MeasurementEntryController<ContinuityOfSmallResistanceEntryDto> {

    @Autowired
    private ContinuityOfSmallResistanceEntryDtoRepository entryRepository;

    @Override
    public List<ContinuityOfSmallResistanceEntryDto> getEntries() {

        return entryRepository.findAll();
    }

    @Override
    public ContinuityOfSmallResistanceEntryDto addEntry(ContinuityOfSmallResistanceEntryDto entry) {

        entryRepository.save(entry);
        return entry;
    }
}
