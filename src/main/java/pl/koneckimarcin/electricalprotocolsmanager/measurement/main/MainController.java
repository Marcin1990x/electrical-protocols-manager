package pl.koneckimarcin.electricalprotocolsmanager.measurement.main;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.data.TextData;

import java.util.List;

@RestController
public class MainController {

    @GetMapping("/types")
    public List<String> retrieveMeasurementMainTypes() {
        return TextData.measurementsMainNames;
    }
}
