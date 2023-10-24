package pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTnc.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTnc.entry.CircuitInsulationResistanceTncEntryDto;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTnc.entry.CircuitInsulationResistanceTncEntryDtoRepository;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.main.MeasurementMainController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/2")
public class CircuitInsulationResistanceTncDtoController
        implements MeasurementMainController<CircuitInsulationResistanceTncDto> {

    @Autowired
    private CircuitInsulationResistanceTncDtoRepository mainRepository;
    @Autowired
    private CircuitInsulationResistanceTncEntryDtoRepository entryRepository;


    @Override
    public List<CircuitInsulationResistanceTncDto> getMains() {

        return mainRepository.findAll();
    }

    @Override
    public CircuitInsulationResistanceTncDto addMain(CircuitInsulationResistanceTncDto main) {

        mainRepository.save(main);
        return main;
    }

    @Override
    public CircuitInsulationResistanceTncDto addEntryToMain(int mainId, int entryId) {

        Optional<CircuitInsulationResistanceTncDto> main = mainRepository.findById(mainId);
        Optional<CircuitInsulationResistanceTncEntryDto> entry = entryRepository.findById(entryId);

        main.get().addEntry(entry.get());
        mainRepository.save(main.get());

        return main.get();
    }
}
