package pl.koneckimarcin.electricalprotocolsmanager.measurement.soilResistance.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.main.MeasurementMainService;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.soilResistance.entry.SoilResistanceEntry;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.soilResistance.entry.SoilResistanceEntryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SoilResistanceService implements MeasurementMainService<SoilResistance> {

    @Autowired
    private SoilResistanceRepository mainRepository;
    @Autowired
    private SoilResistanceEntryRepository entryRepository;

    @Override
    public List<SoilResistance> getMains() {

        return mainRepository.findAll();
    }

    @Override
    public SoilResistance addMain(SoilResistance main) {

        mainRepository.save(main);
        return main;
    }

    @Override
    public SoilResistance addEntryToMain(int mainId, int entryId) {

        Optional<SoilResistance> main = mainRepository.findById(mainId);
        Optional<SoilResistanceEntry> entry = entryRepository.findById(entryId);

        main.get().addEntry(entry.get());
        mainRepository.save(main.get());

        return main.get();
    }
}
