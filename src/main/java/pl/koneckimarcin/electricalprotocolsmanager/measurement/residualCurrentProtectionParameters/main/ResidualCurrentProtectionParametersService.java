package pl.koneckimarcin.electricalprotocolsmanager.measurement.residualCurrentProtectionParameters.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.main.MeasurementMainService;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.residualCurrentProtectionParameters.entry.ResidualCurrentProtectionParametersEntry;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.residualCurrentProtectionParameters.entry.ResidualCurrentProtectionParametersEntryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ResidualCurrentProtectionParametersService implements MeasurementMainService<ResidualCurrentProtectionParameters> {

    @Autowired
    private ResidualCurrentProtectionParametersRepository mainRepository;
    @Autowired
    private ResidualCurrentProtectionParametersEntryRepository entryRepository;

    @Override
    public List<ResidualCurrentProtectionParameters> getMains() {

        return mainRepository.findAll();
    }

    @Override
    public ResidualCurrentProtectionParameters addMain(ResidualCurrentProtectionParameters main) {

        mainRepository.save(main);
        return main;
    }

    @Override
    public ResidualCurrentProtectionParameters addEntryToMain(int mainId, int entryId) {

        Optional<ResidualCurrentProtectionParameters> main = mainRepository.findById(mainId);
        Optional<ResidualCurrentProtectionParametersEntry> entry = entryRepository.findById(entryId);

        main.get().addEntry(entry.get());
        mainRepository.save(main.get());

        return main.get();
    }
}
