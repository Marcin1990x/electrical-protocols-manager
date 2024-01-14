package pl.koneckimarcin.electricalprotocolsmanager.measurement.protectionAgainstElectricShockByAutomaticShutdown.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.main.MeasurementMainService;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.protectionAgainstElectricShockByAutomaticShutdown.entry.ProtectionMeasurementEntry;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.protectionAgainstElectricShockByAutomaticShutdown.entry.ProtectionMeasurementEntryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProtectionAgainstElectricShockByAutomaticShutdownService
        implements MeasurementMainService<ProtectionAgainstElectricShockByAutomaticShutdown> {

    @Autowired
    private ProtectionAgainstElectricShockByAutomaticShutdownRepository mainRepository;
    @Autowired
    private ProtectionMeasurementEntryRepository entryRepository;

    @Override
    public List<ProtectionAgainstElectricShockByAutomaticShutdown> getMains() {

        return mainRepository.findAll();
    }

    @Override
    public ProtectionAgainstElectricShockByAutomaticShutdown addMain(ProtectionAgainstElectricShockByAutomaticShutdown main) {

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

    public ProtectionAgainstElectricShockByAutomaticShutdown updateMain(int mainId, ProtectionAgainstElectricShockByAutomaticShutdown newMain) {

        Optional<ProtectionAgainstElectricShockByAutomaticShutdown> mainToUpdate =
                mainRepository.findById(mainId);

        if (mainToUpdate.isPresent()) {
            mainToUpdate.get().setUn(newMain.getUn());
            mainToUpdate.get().setUi(newMain.getUi());
            mainToUpdate.get().setKo(newMain.getKo());
            mainToUpdate.get().setTa(newMain.getTa());
            mainToUpdate.get().setNetworkType(newMain.getNetworkType());

            mainRepository.save(mainToUpdate.get());
        }
        return mainToUpdate.get();
    }
}
