package pl.koneckimarcin.electricalprotocolsmanager.utilities.electrician;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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

    @GetMapping("/test")
    public void test() throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        //List<Electrician> testowe = new ArrayList<>(mapper.readValue(new File(("electricians.json")), Electrician.class));

        //System.out.println(testowe);
    }


    @PostMapping()
    public Electrician addElectrician(@RequestBody Electrician electrician) throws IOException {

        Electrician createdElectrician = electrician;
        createdElectrician.addSignature();
        System.out.println(createdElectrician.getSignature());
        repository.save(createdElectrician);

        service.saveElectriciansToFile();

        return createdElectrician;
    }
}
