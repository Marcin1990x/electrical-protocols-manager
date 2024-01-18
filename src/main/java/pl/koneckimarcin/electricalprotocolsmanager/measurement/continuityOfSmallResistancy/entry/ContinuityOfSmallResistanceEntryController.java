package pl.koneckimarcin.electricalprotocolsmanager.measurement.continuityOfSmallResistancy.entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.entry.MeasurementEntryController;

import java.util.List;

@RestController
@RequestMapping("/5")
public class ContinuityOfSmallResistanceEntryController
        implements MeasurementEntryController<ContinuityOfSmallResistanceEntry> {

    @Autowired
    private ContinuityOfSmallResistanceEntryService entryService;

    @Override
    public List<ContinuityOfSmallResistanceEntry> getEntries() {

        return entryService.getEntries();
    }

    @Override
    public ContinuityOfSmallResistanceEntry addEntry(ContinuityOfSmallResistanceEntry entry) {

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

    @Override
    public ContinuityOfSmallResistanceEntry updateEntry(int entryId, ContinuityOfSmallResistanceEntry entry) {

        return entryService.updateEntry(entryId, entry);
    }
}
