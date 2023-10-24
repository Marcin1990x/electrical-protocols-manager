package pl.koneckimarcin.electricalprotocolsmanager.measurement.continuityOfSmallResistancy.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.continuityOfSmallResistancy.entry.ContinuityOfSmallResistanceEntryDto;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.continuityOfSmallResistancy.entry.ContinuityOfSmallResistanceEntryDtoRepository;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.main.MeasurementMainController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/5")
public class ContinuityOfSmallResistanceDtoController
        implements MeasurementMainController<ContinuityOfSmallResistanceDto> {

    @Autowired
    private ContinuityOfSmallResistanceDtoRepository mainRepository;
    @Autowired
    private ContinuityOfSmallResistanceEntryDtoRepository entryRepository;

    @Override
    public List<ContinuityOfSmallResistanceDto> getMains() {

        return mainRepository.findAll();
    }

    @Override
    public ContinuityOfSmallResistanceDto addMain(ContinuityOfSmallResistanceDto main) {

        mainRepository.save(main);
        return main;
    }

    @Override
    public ContinuityOfSmallResistanceDto addEntryToMain(int mainId, int entryId) {

        Optional<ContinuityOfSmallResistanceDto> main = mainRepository.findById(mainId);
        Optional<ContinuityOfSmallResistanceEntryDto> entry = entryRepository.findById(entryId);

        main.get().addEntry(entry.get());
        mainRepository.save(main.get());

        return main.get();
    }
}
