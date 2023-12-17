package pl.koneckimarcin.electricalprotocolsmanager.measurement.continuityOfSmallResistancy.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.continuityOfSmallResistancy.entry.ContinuityOfSmallResistanceEntry;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.continuityOfSmallResistancy.entry.ContinuityOfSmallResistanceEntryRepository;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.main.MeasurementMainService;

import java.util.List;
import java.util.Optional;

@Service
public class ContinuityOfSmallResistanceService implements MeasurementMainService<ContinuityOfSmallResistance> {

    @Autowired
    private ContinuityOfSmallResistanceRepository mainRepository;
    @Autowired
    private ContinuityOfSmallResistanceEntryRepository entryRepository;

    @Override
    public List<ContinuityOfSmallResistance> getMains() {

        return mainRepository.findAll();
    }

    @Override
    public ContinuityOfSmallResistance addMain(ContinuityOfSmallResistance main) {

        mainRepository.save(main);
        return main;
    }

    @Override
    public ContinuityOfSmallResistance addEntryToMain(int mainId, int entryId) {

        Optional<ContinuityOfSmallResistance> main = mainRepository.findById(mainId);
        Optional<ContinuityOfSmallResistanceEntry> entry = entryRepository.findById(entryId);

        main.get().addEntry(entry.get());
        mainRepository.save(main.get());

        return main.get();
    }
}
