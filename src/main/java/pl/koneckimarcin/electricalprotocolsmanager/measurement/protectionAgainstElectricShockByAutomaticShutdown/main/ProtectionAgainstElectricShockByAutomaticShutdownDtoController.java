package pl.koneckimarcin.electricalprotocolsmanager.measurement.protectionAgainstElectricShockByAutomaticShutdown.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.main.MeasurementMainController;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.protectionAgainstElectricShockByAutomaticShutdown.entry.ProtectionMeasurementEntryDto;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.protectionAgainstElectricShockByAutomaticShutdown.entry.ProtectionMeasurementEntryDtoRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/0")
public class ProtectionAgainstElectricShockByAutomaticShutdownDtoController
        implements MeasurementMainController<ProtectionAgainstElectricShockByAutomaticShutdownDto> {

    @Autowired
    private ProtectionAgainstElectricShockByAutomaticShutdownDtoRepository mainRepository;
    @Autowired
    private ProtectionMeasurementEntryDtoRepository entryRepository;

    @Override
    public List<ProtectionAgainstElectricShockByAutomaticShutdownDto> getMains() {

        return mainRepository.findAll();
    }

    @Override
    public ProtectionAgainstElectricShockByAutomaticShutdownDto
    addMain(ProtectionAgainstElectricShockByAutomaticShutdownDto main) {

        mainRepository.save(main);
        return main;
    }

    @Override
    public ProtectionAgainstElectricShockByAutomaticShutdownDto addEntryToMain(int mainId, int entryId) {

        Optional<ProtectionAgainstElectricShockByAutomaticShutdownDto> main = mainRepository.findById(mainId);
        Optional<ProtectionMeasurementEntryDto> entry = entryRepository.findById(entryId);

        main.get().addEntry(entry.get());
        mainRepository.save(main.get());

        return main.get();
    }
}
