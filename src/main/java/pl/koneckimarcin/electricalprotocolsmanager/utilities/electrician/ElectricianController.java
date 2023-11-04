package pl.koneckimarcin.electricalprotocolsmanager.utilities.electrician;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/electricians")
public class ElectricianController {

    ElectricianRepository repository;

    public ElectricianController(ElectricianRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Electrician> getElectricians() {

        return repository.findAll();
    }

    @PostMapping()
    public Electrician addElectrician(@RequestBody Electrician electrician) {

        Electrician createdElectrician = electrician;
        createdElectrician.addSignature();
        System.out.println(createdElectrician.getSignature());
        repository.save(createdElectrician);

        return createdElectrician;
    }
}
