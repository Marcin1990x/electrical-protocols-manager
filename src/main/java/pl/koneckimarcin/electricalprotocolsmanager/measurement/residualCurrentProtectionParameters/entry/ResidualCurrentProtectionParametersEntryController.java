package pl.koneckimarcin.electricalprotocolsmanager.measurement.residualCurrentProtectionParameters.entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.entry.MeasurementEntryController;

import java.util.List;

@RestController
@RequestMapping("/3")
public class ResidualCurrentProtectionParametersEntryController
        implements MeasurementEntryController<ResidualCurrentProtectionParametersEntry> {

    @Autowired
    private ResidualCurrentProtectionParametersEntryRepository entryRepository;

    @Override
    public List<ResidualCurrentProtectionParametersEntry> getEntries() {

        return entryRepository.findAll();
    }

    @Override
    public ResidualCurrentProtectionParametersEntry addEntry(ResidualCurrentProtectionParametersEntry entry) {

        entry.setResult();
        entryRepository.save(entry);
        return entry;
    }
}
