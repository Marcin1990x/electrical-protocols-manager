package pl.koneckimarcin.electricalprotocolsmanager.utilities.electrician;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.titlePage.PdfTitlePage;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.titlePage.PdfTitlePageRepository;

import java.io.IOException;
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

        List<Electrician> distinctElectricians;

        List<Electrician> allElectricians = getElectricians();
        List<PdfTitlePage> titlePageData = titlePageRepository.findAll();

        distinctElectricians = service.retrieveDistinctElectricians(allElectricians, titlePageData);

        return distinctElectricians;
    }

    @PostMapping()
    public ResponseEntity<Object> addElectrician(@RequestBody Electrician electrician) throws IOException {

        Electrician createdElectrician = null;

        if (repository.findByLastName(electrician.getLastName()).size() == 0) {

            createdElectrician = electrician;
            createdElectrician.addSignature();
            repository.save(createdElectrician);
            return new ResponseEntity<>(createdElectrician, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(
                    "Error 101. Electrician with last name '" + electrician.getLastName() + "' already exists.",
                    HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteElectricianById(@PathVariable int id) throws IOException {

        List<PdfTitlePage> titlePageData = titlePageRepository.findAll();

        if (titlePageData.size() > 0) {
            if (titlePageData.stream().findFirst()
                    .get().getElectricians().stream()
                    .filter(elec -> elec.getId() == id)
                    .findFirst().isEmpty()) {
                repository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Error 100. Can not delete Electrician, " +
                        "please first delete it from title page.", HttpStatus.BAD_REQUEST);
            }
        } else {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
