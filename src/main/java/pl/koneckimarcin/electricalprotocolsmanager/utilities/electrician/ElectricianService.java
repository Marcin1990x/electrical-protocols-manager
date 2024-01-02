package pl.koneckimarcin.electricalprotocolsmanager.utilities.electrician;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.titlePage.PdfTitlePage;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.titlePage.PdfTitlePageRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ElectricianService {

    @Autowired
    private ElectricianRepository electricianRepository;
    @Autowired
    private PdfTitlePageRepository titlePageRepository;

    public List<Electrician> getElectricians() {

        return electricianRepository.findAll();
    }

    public List<Electrician> getDistinctElectricians() {

        List<Electrician> distinctElectricians;

        List<Electrician> allElectricians = getElectricians();
        List<PdfTitlePage> titlePageData = titlePageRepository.findAll();

        distinctElectricians = retrieveDistinctElectricians(allElectricians, titlePageData);

        return distinctElectricians;
    }
    private List<Electrician> retrieveDistinctElectricians(List<Electrician> allElectricians, List<PdfTitlePage> titlePageData) {

        List<Electrician> distinctElectricians = new ArrayList<>();

        if (titlePageData.size() == 0 ||
                titlePageData.stream().findFirst().get().getElectricians().isEmpty()
        ) {
            return allElectricians;
        } else {
            List<Electrician> addedElectricians = titlePageData.stream().findFirst().get().getElectricians();

            for (Electrician electrician : allElectricians) {
                if (
                        addedElectricians.stream()
                                .filter(elec -> (
                                        elec.getLastName().equals(electrician.getLastName()) &&
                                                elec.getFirstName().equals(electrician.getFirstName())
                                )).findFirst().isEmpty()
                ) {
                    distinctElectricians.add(electrician);
                }
            }
            return distinctElectricians;
        }
    }

    public ResponseEntity<Object> addElectrician(Electrician electrician) {

        Electrician createdElectrician;

        if (electricianRepository.findByLastName(electrician.getLastName()).isEmpty()) {
            createdElectrician = electrician;
            createdElectrician.addSignature();
            electricianRepository.save(createdElectrician);
            return new ResponseEntity<>(createdElectrician, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(
                    "Error 101. Electrician with last name '" + electrician.getLastName() + "' already exists.",
                    HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> deleteElectricianById(int id) {

        List<PdfTitlePage> titlePageData = titlePageRepository.findAll();

        if (titlePageData.size() > 0) {
            if (isElectricianNotAddedToTitlePageData(titlePageData, id)) {
                electricianRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Error 100. Can not delete Electrician, " +
                        "please first delete it from title page.", HttpStatus.BAD_REQUEST);
            }
        } else {
            electricianRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
    private boolean isElectricianNotAddedToTitlePageData(List<PdfTitlePage> titlePageData, int id) {

        return titlePageData.stream().findFirst()
                .get().getElectricians().stream()
                .filter(elec -> elec.getId() == id)
                .findFirst().isEmpty();
    }
}
