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
        implements MeasurementEntryService<ResidualCurrentProtectionParametersEntry> {

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

    public ResidualCurrentProtectionParametersEntry updateEntry
            (int entryId, ResidualCurrentProtectionParametersEntry newEntry) {

        Optional<ResidualCurrentProtectionParametersEntry> entryToUpdate =
                entryRepository.findById(entryId);

        if (entryToUpdate.isPresent()) {
            entryToUpdate.get().setSymbol(newEntry.getSymbol());
            entryToUpdate.get().setMeasuringPoint(newEntry.getMeasuringPoint());
            entryToUpdate.get().setCircuitBreaker(newEntry.getCircuitBreaker());
            entryToUpdate.get().setRcdType(newEntry.getRcdType());
            entryToUpdate.get().setiNom(newEntry.getiNom());
            entryToUpdate.get().setIa(newEntry.getIa());
            entryToUpdate.get().setTa(newEntry.getTa());
            entryToUpdate.get().setTrcd(newEntry.getTrcd());
            entryToUpdate.get().setUb(newEntry.getUb());
            entryToUpdate.get().setUi(newEntry.getUi());

            entryToUpdate.get().setResult();

            entryRepository.save(entryToUpdate.get());
        }
        return entryToUpdate.get();
    }
}
