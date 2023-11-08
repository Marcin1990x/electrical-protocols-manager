package pl.koneckimarcin.electricalprotocolsmanager.pdf.titlePage;

import org.springframework.web.bind.annotation.*;
import pl.koneckimarcin.electricalprotocolsmanager.utilities.electrician.Electrician;
import pl.koneckimarcin.electricalprotocolsmanager.utilities.electrician.ElectricianRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/titlePage")
public class PdfTitlePageController {

    private PdfTitlePageRepository titlePageRepository;
    private ElectricianRepository electricianRepository;

    public PdfTitlePageController(PdfTitlePageRepository titlePageRepository, ElectricianRepository electricianRepository) {
        this.titlePageRepository = titlePageRepository;
        this.electricianRepository = electricianRepository;
    }

    @GetMapping
    public PdfTitlePage getTitlePage(){

        List<PdfTitlePage> titlePage = titlePageRepository.findAll();
        if(titlePage.size() > 0) {
            return titlePage.stream().findFirst().get();
        } else {
            return null;
        }
    }

    @PostMapping
    public PdfTitlePage addTitlePage(@RequestBody PdfTitlePage titlePage){

        titlePageRepository.deleteAll();
        titlePageRepository.save(titlePage);

        return titlePage;
    }
    @PutMapping("/add/{id}")
    PdfTitlePage addElectricianToTitlePage(@PathVariable int id,  @RequestParam int electricianId){

        Optional<PdfTitlePage> titlePage = titlePageRepository.findById(id);
        Optional<Electrician> electricianToAdd = electricianRepository.findById(electricianId);
        titlePage.get().addElectrician(electricianToAdd.get());

        titlePageRepository.save(titlePage.get());

        return titlePage.get();
    }
    @PutMapping("/remove/{id}")
    PdfTitlePage removeElectricianFromTitlePage(@PathVariable int id, @RequestParam int electricianId){

        Optional<PdfTitlePage> titlePage = titlePageRepository.findById(id);
        Optional<Electrician> electricianToRemove = electricianRepository.findById(electricianId);
        titlePage.get().removeElectrician(electricianToRemove.get());

        titlePageRepository.save(titlePage.get());

        return titlePage.get();
    }
}
