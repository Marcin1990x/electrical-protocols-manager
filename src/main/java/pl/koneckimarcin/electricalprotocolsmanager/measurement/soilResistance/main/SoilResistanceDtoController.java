package pl.koneckimarcin.electricalprotocolsmanager.measurement.soilResistance.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.main.MeasurementMainController;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.soilResistance.entry.SoilResistanceEntryDto;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.soilResistance.entry.SoilResistanceEntryDtoRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/4")
public class SoilResistanceDtoController
        implements MeasurementMainController<SoilResistanceDto> {

    @Autowired
    private SoilResistanceDtoRepository mainRepository;
    @Autowired
    private SoilResistanceEntryDtoRepository entryRepository;

    @Override
    public List<SoilResistanceDto> getMains() {

        return mainRepository.findAll();
    }

    @Override
    public SoilResistanceDto addMain(SoilResistanceDto main) {

        mainRepository.save(main);
        return main;
    }

    @Override
    public SoilResistanceDto addEntryToMain(int mainId, int entryId) {

        Optional<SoilResistanceDto> main = mainRepository.findById(mainId);
        Optional<SoilResistanceEntryDto> entry = entryRepository.findById(entryId);

        main.get().addEntry(entry.get());
        mainRepository.save(main.get());

        return main.get();
    }
}
