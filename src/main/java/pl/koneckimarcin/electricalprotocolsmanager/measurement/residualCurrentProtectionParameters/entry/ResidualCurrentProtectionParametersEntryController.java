package pl.koneckimarcin.electricalprotocolsmanager.measurement.residualCurrentProtectionParameters.entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.entry.MeasurementEntryController;

import java.util.List;

@RestController
@RequestMapping("/3")
public class ResidualCurrentProtectionParametersEntryController
        implements MeasurementEntryController<ResidualCurrentProtectionParametersEntry> {

    @Autowired
    private ResidualCurrentProtectionParametersEntryService entryService;

    @Override
    public List<ResidualCurrentProtectionParametersEntry> getEntries() {

        return entryService.getEntries();
    }

    @Override
    public ResidualCurrentProtectionParametersEntry addEntry(ResidualCurrentProtectionParametersEntry entry) {

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
    public ResidualCurrentProtectionParametersEntry updateEntry(
            @PathVariable int entryId, @RequestBody ResidualCurrentProtectionParametersEntry entry) {

        return entryService.updateEntry(entryId, entry);
    }
}
