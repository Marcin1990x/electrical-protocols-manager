package pl.koneckimarcin.electricalprotocolsmanager.measurement.soilResistance.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.main.MeasurementMainController;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.soilResistance.entry.SoilResistanceEntry;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.soilResistance.entry.SoilResistanceEntryRepository;
import pl.koneckimarcin.electricalprotocolsmanager.structure.room.RoomRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/4")
public class SoilResistanceController
        implements MeasurementMainController<SoilResistance> {

    @Autowired
    private SoilResistanceRepository mainRepository;
    @Autowired
    private SoilResistanceEntryRepository entryRepository;
    @Autowired
    private RoomRepository roomRepository;

    @Override
    public List<SoilResistance> getMains() {

        return mainRepository.findAll();
    }

    @Override
    public SoilResistance addMain(SoilResistance main) {

        mainRepository.save(main);
        return main;
    }

    @Override
    public SoilResistance addEntryToMain(int mainId, int entryId) {

        Optional<SoilResistance> main = mainRepository.findById(mainId);
        Optional<SoilResistanceEntry> entry = entryRepository.findById(entryId);

        main.get().addEntry(entry.get());
        mainRepository.save(main.get());

        return main.get();
    }
}
