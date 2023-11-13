package pl.koneckimarcin.electricalprotocolsmanager.utilities.electrician;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.titlePage.PdfTitlePage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ElectricianService {

    ElectricianRepository repository;

    public ElectricianService(ElectricianRepository repository) {
        this.repository = repository;
    }

    public void saveElectriciansToFile() throws IOException {

        List<Electrician> electricians = repository.findAll();

        ObjectMapper mapper = new ObjectMapper();

        File file = new File(("electricians.json"));
        mapper.writeValue(file, electricians);
    }

    public List<Electrician> loadElectriciansFromFile() throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        List<Electrician> electricianList = mapper
                .readValue(new File("electricians.json"), new TypeReference<List<Electrician>>() {});

        return electricianList;
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
                if(
                        addedElectricians.stream()
                                .filter(elec -> (
                                        elec.getLastName().equals(electrician.getLastName()) &&
                                                elec.getFirstName().equals(electrician.getFirstName())
                                )).findFirst().isEmpty()
                ){
                    distinctElectricians.add(electrician);
                }
            }
            return distinctElectricians;
        }
    }
}
