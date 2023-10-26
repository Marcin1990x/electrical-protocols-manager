package pl.koneckimarcin.electricalprotocolsmanager.measurement.continuityOfSmallResistancy.entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.entry.MeasurementEntryController;

import java.util.List;

@RestController
@RequestMapping("/5")
public class ContinuityOfSmallResistanceEntryController
        implements MeasurementEntryController<ContinuityOfSmallResistanceEntry> {

    @Autowired
    private ContinuityOfSmallResistanceEntryRepository entryRepository;

    @Override
    public List<ContinuityOfSmallResistanceEntry> getEntries() {

        return entryRepository.findAll();
    }

    @Override
    public ContinuityOfSmallResistanceEntry addEntry(ContinuityOfSmallResistanceEntry entry) {

        entry.setResult();
        entryRepository.save(entry);
        return entry;
    }
}
