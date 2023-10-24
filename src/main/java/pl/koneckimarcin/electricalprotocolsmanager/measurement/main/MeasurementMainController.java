package pl.koneckimarcin.electricalprotocolsmanager.measurement.main;

import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface MeasurementMainController <T> {

    @GetMapping("/mains")
    public List<T> getMains();

    @PostMapping("/mains")
    public T addMain(@RequestBody T t);

    @PutMapping("/mains/{mainId}")
    public T addEntryToMain(@PathVariable int mainId, @RequestParam int entryId);
}
