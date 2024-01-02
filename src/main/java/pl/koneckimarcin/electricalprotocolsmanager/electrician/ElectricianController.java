package pl.koneckimarcin.electricalprotocolsmanager.electrician;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/electricians")
public class ElectricianController {

    @Autowired
    private ElectricianService electricianService;

    @GetMapping
    public List<Electrician> getElectricians() {

        return electricianService.getElectricians();
    }

    @GetMapping("/distinct")
    public List<Electrician> getDistinctElectricians() {

        return electricianService.getDistinctElectricians();
    }

    @PostMapping()
    public ResponseEntity<Object> addElectrician(@RequestBody Electrician electrician) {

        return electricianService.addElectrician(electrician);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteElectricianById(@PathVariable int id) {

        return electricianService.deleteElectricianById(id);
    }
}
