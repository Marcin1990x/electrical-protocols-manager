package pl.koneckimarcin.electricalprotocolsmanager.utilities.electrician;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
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
}
