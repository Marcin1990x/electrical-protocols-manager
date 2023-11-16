package pl.koneckimarcin.electricalprotocolsmanager.measurement.residualCurrentProtectionParameters.entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.entry.MeasurementEntryController;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.residualCurrentProtectionParameters.main.ResidualCurrentProtectionParameters;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.residualCurrentProtectionParameters.main.ResidualCurrentProtectionParametersRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/3")
public class ResidualCurrentProtectionParametersEntryController
        implements MeasurementEntryController<ResidualCurrentProtectionParametersEntry> {

    @Autowired
    private ResidualCurrentProtectionParametersEntryRepository entryRepository;
    @Autowired
    private ResidualCurrentProtectionParametersRepository mainRepository;

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

    @Override
    public void deleteEntryById(int id, int mainId) {

        Optional<ResidualCurrentProtectionParameters> main = mainRepository.findById(mainId);
        Optional<ResidualCurrentProtectionParametersEntry> entry = entryRepository.findById(id);
        main.get().removeEntry(entry.get());

        entryRepository.deleteById(id);
    }

    @Override
    public void deleteAllEntries(int mainId) {

        Optional<ResidualCurrentProtectionParameters> main = mainRepository.findById(mainId);
        List<Integer> entriesToDelete = main.get().listEntriesId();
        main.get().removeAllEntries();

        for (Integer entryId : entriesToDelete) {
            entryRepository.deleteById(entryId);
        }
    }
}
