package pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTnc.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTnc.entry.CircuitInsulationResistanceTncEntry;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTnc.entry.CircuitInsulationResistanceTncEntryRepository;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.main.MeasurementMainService;

import java.util.List;
import java.util.Optional;

@Service
public class CircuitInsulationResistanceTncService implements MeasurementMainService<CircuitInsulationResistanceTnc> {

    @Autowired
    private CircuitInsulationResistanceTncRepository mainRepository;
    @Autowired
    private CircuitInsulationResistanceTncEntryRepository entryRepository;


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

    public CircuitInsulationResistanceTnc updateMain(int mainId, CircuitInsulationResistanceTnc main) {

        Optional<CircuitInsulationResistanceTnc> mainToUpdate =
                mainRepository.findById(mainId);

        if (mainToUpdate.isPresent()) {
            mainToUpdate.get().setUiso(main.getUiso());
            mainRepository.save(mainToUpdate.get());
        }
        return mainToUpdate.get();
    }
}
