package pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTns.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTns.entry.CircuitInsulationResistanceTnsEntry;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTns.entry.CircuitInsulationResistanceTnsEntryRepository;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.main.MeasurementMainController;
import pl.koneckimarcin.electricalprotocolsmanager.structure.room.RoomRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/1")
public class CircuitInsulationResistanceTnsController
        implements MeasurementMainController<CircuitInsulationResistanceTns> {

    @Autowired
    private CircuitInsulationResistanceTnsRepository mainRepository;
    @Autowired
    private CircuitInsulationResistanceTnsEntryRepository entryRepository;
    @Autowired
    private RoomRepository roomRepository;

    @Override
    public List<CircuitInsulationResistanceTns> getMains() {

        return mainRepository.findAll();
    }

    @Override
    public CircuitInsulationResistanceTns addMain(CircuitInsulationResistanceTns main) {

        mainRepository.save(main);
        return main;
    }

    @Override
    public CircuitInsulationResistanceTns addEntryToMain(int mainId, int entryId) {

        Optional<CircuitInsulationResistanceTns> main = mainRepository.findById(mainId);
        Optional<CircuitInsulationResistanceTnsEntry> entry = entryRepository.findById(entryId);

        main.get().addEntry(entry.get());
        mainRepository.save(main.get());

        return main.get();
    }
}
