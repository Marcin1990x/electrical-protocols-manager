package pl.koneckimarcin.electricalprotocolsmanager.measurement.protectionAgainstElectricShockByAutomaticShutdown.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.main.MeasurementMainController;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.protectionAgainstElectricShockByAutomaticShutdown.entry.ProtectionMeasurementEntry;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.protectionAgainstElectricShockByAutomaticShutdown.entry.ProtectionMeasurementEntryRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/0")
public class ProtectionAgainstElectricShockByAutomaticShutdownController
        implements MeasurementMainController<ProtectionAgainstElectricShockByAutomaticShutdown> {

    @Autowired
    private ProtectionAgainstElectricShockByAutomaticShutdownRepository mainRepository;
    @Autowired
    private ProtectionMeasurementEntryRepository entryRepository;

    @Override
    public List<ProtectionAgainstElectricShockByAutomaticShutdown> getMains() {

        return mainRepository.findAll();
    }

    @Override
    public ProtectionAgainstElectricShockByAutomaticShutdown
    addMain(ProtectionAgainstElectricShockByAutomaticShutdown main) {

        mainRepository.save(main);
        return main;
    }

    @Override
    public ProtectionAgainstElectricShockByAutomaticShutdown addEntryToMain(int mainId, int entryId) {

        Optional<ProtectionAgainstElectricShockByAutomaticShutdown> main = mainRepository.findById(mainId);
        Optional<ProtectionMeasurementEntry> entry = entryRepository.findById(entryId);

        main.get().addEntry(entry.get());
        mainRepository.save(main.get());

        return main.get();
    }
}
