package pl.koneckimarcin.electricalprotocolsmanager.measurement.continuityOfSmallResistancy.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.continuityOfSmallResistancy.entry.ContinuityOfSmallResistanceEntry;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.continuityOfSmallResistancy.entry.ContinuityOfSmallResistanceEntryRepository;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.main.MeasurementMainController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/5")
public class ContinuityOfSmallResistanceController
        implements MeasurementMainController<ContinuityOfSmallResistance> {

    @Autowired
    private ContinuityOfSmallResistanceRepository mainRepository;
    @Autowired
    private ContinuityOfSmallResistanceEntryRepository entryRepository;

    @Override
    public List<ContinuityOfSmallResistance> getMains() {

        return mainRepository.findAll();
    }

    @Override
    public ContinuityOfSmallResistance addMain(ContinuityOfSmallResistance main) {

        mainRepository.save(main);
        return main;
    }

    @Override
    public ContinuityOfSmallResistance addEntryToMain(int mainId, int entryId) {

        Optional<ContinuityOfSmallResistance> main = mainRepository.findById(mainId);
        Optional<ContinuityOfSmallResistanceEntry> entry = entryRepository.findById(entryId);

        main.get().addEntry(entry.get());
        mainRepository.save(main.get());

        return main.get();
    }
}
