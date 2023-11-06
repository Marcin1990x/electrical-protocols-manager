package pl.koneckimarcin.electricalprotocolsmanager.utilities.electrician;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/electricians")
public class ElectricianController {

    ElectricianRepository repository;
    ElectricianService service;

    public ElectricianController(ElectricianRepository repository, ElectricianService service) {
        this.repository = repository;
        this.service = service;
    }

    @GetMapping
    public List<Electrician> getElectricians() {

        return repository.findAll();
    }

    public void addElectricianFromFile(Electrician electrician) throws IOException {

        Electrician createdElectrician = null;

        if (repository.findByLastName(electrician.getLastName()).size() == 0) {
            createdElectrician = electrician;
            repository.save(createdElectrician);
        }
    }
    @PostMapping()
    public Electrician addElectrician(@RequestBody Electrician electrician) throws IOException {

        Electrician createdElectrician = null;

        if (repository.findByLastName(electrician.getLastName()).size() == 0) {

            createdElectrician = electrician;
            createdElectrician.addSignature();
            repository.save(createdElectrician);

            service.saveElectriciansToFile();
        }
        return createdElectrician;
    }

    @DeleteMapping("/{id}")
    public void deleteElectricianById(@PathVariable int id) throws IOException {

        repository.deleteById(id);
        service.saveElectriciansToFile();
    }

    @GetMapping("/loadFromFile")
    public void loadElectriciansFromFile() throws IOException {

        List<Electrician> electricianList = electricianList = service.loadElectriciansFromFile();

        for (Electrician electrician : electricianList) {
            addElectricianFromFile(electrician);
        }
    }
}
