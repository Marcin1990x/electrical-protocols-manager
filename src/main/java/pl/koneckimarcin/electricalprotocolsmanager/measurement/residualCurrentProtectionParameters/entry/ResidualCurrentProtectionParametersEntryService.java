package pl.koneckimarcin.electricalprotocolsmanager.measurement.residualCurrentProtectionParameters.entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.entry.MeasurementEntryService;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.residualCurrentProtectionParameters.main.ResidualCurrentProtectionParameters;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.residualCurrentProtectionParameters.main.ResidualCurrentProtectionParametersRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ResidualCurrentProtectionParametersEntryService
        implements MeasurementEntryService <ResidualCurrentProtectionParametersEntry> {

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
    public void deleteEntryById(int entryId, int mainId) {

        Optional<ResidualCurrentProtectionParameters> main = mainRepository.findById(mainId);
        Optional<ResidualCurrentProtectionParametersEntry> entry = entryRepository.findById(entryId);
        main.get().removeEntry(entry.get());

        entryRepository.deleteById(entryId);
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
