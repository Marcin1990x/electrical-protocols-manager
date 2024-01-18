package pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTns.entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.entry.MeasurementEntryController;

import java.util.List;

@RestController
@RequestMapping("/1")
public class CircuitInsulationResistanceTnsEntryController
        implements MeasurementEntryController<CircuitInsulationResistanceTnsEntry> {

    @Autowired
    private CircuitInsulationResistanceTnsEntryService entryService;

    @Override
    public List<CircuitInsulationResistanceTnsEntry> getEntries() {

        return entryService.getEntries();
    }

    @Override
    public CircuitInsulationResistanceTnsEntry addEntry(CircuitInsulationResistanceTnsEntry entry) {

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
    public CircuitInsulationResistanceTnsEntry updateEntry(
            @PathVariable int entryId, @RequestBody CircuitInsulationResistanceTnsEntry entry) {

        return entryService.updateEntry(entryId, entry);
    }
}
