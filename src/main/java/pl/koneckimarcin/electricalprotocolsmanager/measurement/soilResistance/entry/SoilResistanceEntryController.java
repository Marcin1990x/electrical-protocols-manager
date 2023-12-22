package pl.koneckimarcin.electricalprotocolsmanager.measurement.soilResistance.entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.entry.MeasurementEntryController;

import java.util.List;

@RestController
@RequestMapping("/4")
public class SoilResistanceEntryController
        implements MeasurementEntryController<SoilResistanceEntry> {

    @Autowired
    private SoilResistanceEntryService entryService;

    @Override
    public List<SoilResistanceEntry> getEntries() {

        return entryService.getEntries();
    }

    @Override
    public SoilResistanceEntry addEntry(SoilResistanceEntry entry) {

        return entryService.addEntry(entry);
    }

    @Override
    public void deleteEntryById(int id, int mainId) {

        entryService.deleteEntryById(id, mainId);
    }

    @Override
    public void deleteAllEntries(int mainId) {

        entryService.deleteAllEntries(mainId);
    }
}
