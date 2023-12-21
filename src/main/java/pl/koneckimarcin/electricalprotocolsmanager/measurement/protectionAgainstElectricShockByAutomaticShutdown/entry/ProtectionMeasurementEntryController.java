package pl.koneckimarcin.electricalprotocolsmanager.measurement.protectionAgainstElectricShockByAutomaticShutdown.entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.entry.MeasurementEntryController;

import java.util.List;

@RestController
@RequestMapping("/0")
public class ProtectionMeasurementEntryController
        implements MeasurementEntryController<ProtectionMeasurementEntry> {

    @Autowired
    private ProtectionMeasurementEntryService entryService;

    @Override
    public List<ProtectionMeasurementEntry> getEntries() {

        return entryService.getEntries();
    }

    @Override
    public ProtectionMeasurementEntry addEntry(ProtectionMeasurementEntry entry) {

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
