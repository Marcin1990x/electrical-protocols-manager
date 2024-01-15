package pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTns.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTns.entry.CircuitInsulationResistanceTnsEntry;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTns.entry.CircuitInsulationResistanceTnsEntryRepository;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.main.MeasurementMainService;

import java.util.List;
import java.util.Optional;

@Service
public class CircuitInsulationResistanceTnsService implements MeasurementMainService<CircuitInsulationResistanceTns> {

    @Autowired
    private CircuitInsulationResistanceTnsRepository mainRepository;
    @Autowired
    private CircuitInsulationResistanceTnsEntryRepository entryRepository;

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

    public CircuitInsulationResistanceTns updateMain(int mainId, CircuitInsulationResistanceTns main) {

        Optional<CircuitInsulationResistanceTns> mainToUpdate =
                mainRepository.findById(mainId);

        if (mainToUpdate.isPresent()) {
            mainToUpdate.get().setUiso(main.getUiso());
            mainRepository.save(mainToUpdate.get());
        }
        return mainToUpdate.get();
    }
}
