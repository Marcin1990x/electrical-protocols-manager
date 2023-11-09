package pl.koneckimarcin.electricalprotocolsmanager.utilities.electrician;

import org.springframework.web.bind.annotation.*;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.titlePage.PdfTitlePageRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/electricians")
public class ElectricianController {

    ElectricianRepository repository;
    ElectricianService service;

    PdfTitlePageRepository titlePageRepository;

    public ElectricianController(ElectricianRepository repository, ElectricianService service,
                                 PdfTitlePageRepository titlePageRepository) {
        this.repository = repository;
        this.service = service;
        this.titlePageRepository = titlePageRepository;
    }

    @GetMapping
    public List<Electrician> getElectricians() {

        return repository.findAll();
    }
    @GetMapping("/distinct")
    public List<Electrician> getDistinctElectricians() {

        List<Electrician> distinctElectricians = new ArrayList<>();

        List<Electrician> allElectricians = getElectricians();
        if(titlePageRepository.findAll().size() == 0) {
            return allElectricians;
        } else {
            List<Electrician> addedElectricians = titlePageRepository.findAll()
                    .stream().findFirst().get().getElectricians();
            for(Electrician electrician : allElectricians) {
                for(Electrician electricianAdded : addedElectricians) {
                    if(!(electrician.getLastName().equals(electricianAdded.getLastName())
                    && electrician.getFirstName().equals(electricianAdded.getFirstName()))){
                        distinctElectricians.add(electrician);
                    }
                }
            }
            return distinctElectricians;
        }
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
