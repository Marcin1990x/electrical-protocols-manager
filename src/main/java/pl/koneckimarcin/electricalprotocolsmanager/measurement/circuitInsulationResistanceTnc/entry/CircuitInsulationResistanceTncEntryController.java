package pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTnc.entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.entry.MeasurementEntryController;

import java.util.List;

@RestController
@RequestMapping("/2")
public class CircuitInsulationResistanceTncEntryController
        implements MeasurementEntryController<CircuitInsulationResistanceTncEntry> {

    @Autowired
    private CircuitInsulationResistanceTncEntryService entryService;

    @Override
    public List<CircuitInsulationResistanceTncEntry> getEntries() {

        return entryService.getEntries();
    }

    @Override
    public CircuitInsulationResistanceTncEntry addEntry(CircuitInsulationResistanceTncEntry entry) {

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
    public CircuitInsulationResistanceTncEntry updateEntry(
            @PathVariable int entryId, @RequestBody CircuitInsulationResistanceTncEntry entry) {

        return entryService.updateEntry(entryId, entry);
    }
}
