package pl.koneckimarcin.electricalprotocolsmanager.measurement.entry;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface MeasurementEntryController<T> {

    @GetMapping("/entries")
    public List<T> getEntries();

    @PostMapping("/entries")
    public T addEntry(@RequestBody T t);

}
