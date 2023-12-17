package pl.koneckimarcin.electricalprotocolsmanager.measurement.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MainController {

    @Autowired
    private MainService mainService;

    @GetMapping("/types")
    public List<String> getMeasurementMainTypes() {

        return mainService.getMeasurementMainTypes();
    }

    @GetMapping("/measurements/{id}")
    public MeasurementMain getMeasurementMainById(@PathVariable int id) {

        return mainService.getMeasurementMainById(id);
    }

    @DeleteMapping("mains/{id}")
    public void deleteMeasurementMainById(@PathVariable int id, @RequestParam int roomId) {

        mainService.deleteMeasurementMainById(id, roomId);
    }
}
