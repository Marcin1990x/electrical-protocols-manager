package pl.koneckimarcin.electricalprotocolsmanager.measurement.entry;


import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTns.entry.CircuitInsulationResistanceTnsEntry;

import java.util.List;

public interface MeasurementEntryController<T> {

    @GetMapping("/entries")
    public List<T> getEntries();

    @PostMapping("/entries")
    public T addEntry(@Valid @RequestBody T t);

    @DeleteMapping("/entries/{id}")
    public void deleteEntryById(@PathVariable int id, @RequestParam int mainId);

    @DeleteMapping("/entries")
    public void deleteAllEntries(@RequestParam int mainId);

    @PutMapping("/entries/edit={entryId}")
    public T updateEntry(
            @PathVariable int entryId, @RequestBody T entry);
}
