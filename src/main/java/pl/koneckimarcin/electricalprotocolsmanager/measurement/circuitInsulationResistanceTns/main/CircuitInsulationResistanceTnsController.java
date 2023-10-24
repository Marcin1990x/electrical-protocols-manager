package pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTns.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTns.entry.CircuitInsulationResistanceTnsEntryDto;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTns.entry.CircuitInsulationResistanceTnsEntryDtoRepository;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.main.MeasurementMainController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/1")
public class CircuitInsulationResistanceTnsController
        implements MeasurementMainController<CircuitInsulationResistanceTnsDto> {

    @Autowired
    private CircuitInsulationResistanceTnsDtoRepository mainRepository;
    @Autowired
    private CircuitInsulationResistanceTnsEntryDtoRepository entryRepository;

    @Override
    public List<CircuitInsulationResistanceTnsDto> getMains() {

        return mainRepository.findAll();
    }

    @Override
    public CircuitInsulationResistanceTnsDto addMain(CircuitInsulationResistanceTnsDto main) {

        mainRepository.save(main);
        return main;
    }

    @Override
    public CircuitInsulationResistanceTnsDto addEntryToMain(int mainId, int entryId) {

        Optional<CircuitInsulationResistanceTnsDto> main = mainRepository.findById(mainId);
        Optional<CircuitInsulationResistanceTnsEntryDto> entry = entryRepository.findById(entryId);

        main.get().addEntry(entry.get());
        mainRepository.save(main.get());

        return main.get();
    }
}
