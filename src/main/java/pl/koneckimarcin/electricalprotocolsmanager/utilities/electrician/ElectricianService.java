package pl.koneckimarcin.electricalprotocolsmanager.utilities.electrician;

import org.springframework.stereotype.Service;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.titlePage.PdfTitlePage;

import java.util.ArrayList;
import java.util.List;

@Service
public class ElectricianService {

    ElectricianRepository repository;

    public ElectricianService(ElectricianRepository repository) {
        this.repository = repository;
    }

    public List<Electrician> retrieveDistinctElectricians(List<Electrician> allElectricians, List<PdfTitlePage> titlePageData) {

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
}
