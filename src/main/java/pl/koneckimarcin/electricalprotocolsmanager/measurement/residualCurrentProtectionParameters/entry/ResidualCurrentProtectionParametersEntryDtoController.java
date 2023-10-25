package pl.koneckimarcin.electricalprotocolsmanager.measurement.residualCurrentProtectionParameters.entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.entry.MeasurementEntryController;

import java.util.List;

@RestController
@RequestMapping("/3")
public class ResidualCurrentProtectionParametersEntryDtoController
        implements MeasurementEntryController<ResidualCurrentProtectionParametersEntryDto> {

    @Autowired
    private ResidualCurrentProtectionParametersEntryDtoRepository entryRepository;

    @Override
    public List<ResidualCurrentProtectionParametersEntryDto> getEntries() {

        return entryRepository.findAll();
    }

    @Override
    public ResidualCurrentProtectionParametersEntryDto addEntry(ResidualCurrentProtectionParametersEntryDto entry) {

        entry.setResult();
        entryRepository.save(entry);
        return entry;
    }
}
