package pl.koneckimarcin.electricalprotocolsmanager.structure.building;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BuildingService {

    public String saveBuildingToFile(List<Building> buildings, String projectName) throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        if (buildings.size() == 1) {
            Building building = buildings.stream().findFirst().get();

            File file = new File(("savedProjects\\" + projectName + ".json"));
            mapper.writeValue(file, building);

            return projectName;

        } else return "not saved.";
    }

    public List<String> listFilesInDirectory() {

        return Stream.of(new File("savedProjects\\").listFiles())
                .map(File::getName)
                .map(name -> name.substring(0, name.length() - 5))
                .collect(Collectors.toList());
    }
}
