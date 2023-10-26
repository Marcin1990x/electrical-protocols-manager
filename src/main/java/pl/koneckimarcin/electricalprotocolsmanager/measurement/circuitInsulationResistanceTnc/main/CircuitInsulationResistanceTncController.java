package pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTnc.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTnc.entry.CircuitInsulationResistanceTncEntry;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTnc.entry.CircuitInsulationResistanceTncEntryDtoRepository;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.main.MeasurementMainController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/2")
public class CircuitInsulationResistanceTncController
        implements MeasurementMainController<CircuitInsulationResistanceTnc> {

    @Autowired
    private CircuitInsulationResistanceTncRepository mainRepository;
    @Autowired
    private CircuitInsulationResistanceTncEntryDtoRepository entryRepository;


    @Override
    public List<CircuitInsulationResistanceTnc> getMains() {

        return mainRepository.findAll();
    }

    @Override
    public CircuitInsulationResistanceTnc addMain(CircuitInsulationResistanceTnc main) {

        mainRepository.save(main);
        return main;
    }

    @Override
    public CircuitInsulationResistanceTnc addEntryToMain(int mainId, int entryId) {

        Optional<CircuitInsulationResistanceTnc> main = mainRepository.findById(mainId);
        Optional<CircuitInsulationResistanceTncEntry> entry = entryRepository.findById(entryId);

        main.get().addEntry(entry.get());
        mainRepository.save(main.get());

        return main.get();
    }
}
