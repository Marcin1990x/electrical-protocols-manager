package pl.koneckimarcin.electricalprotocolsmanager.measurement.main;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.data.TextData;

import java.util.List;
import java.util.Optional;

@RestController
public class MainController {

    private MeasurementMainRepository repository;

    public MainController(MeasurementMainRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/types")
    public List<String> retrieveMeasurementMainTypes() {
        return TextData.measurementsMainNames;
    }

    @GetMapping("/measurements/{id}")
    public MeasurementMain getMeasurementMainById(@PathVariable int id) {
        Optional<MeasurementMain> main = repository.findById(id);
        return main.get();
    }
}
