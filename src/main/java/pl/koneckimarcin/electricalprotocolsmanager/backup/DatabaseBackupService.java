package pl.koneckimarcin.electricalprotocolsmanager.backup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DatabaseBackupService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void dumpTables() {

        jdbcTemplate.queryForList("SCRIPT TO 'actualSchema.sql'");
    }

    public void createQueries() {

        List<String> allQueries = new ArrayList<>();

        String result = "";
        StringBuilder builder = new StringBuilder(result);

        BufferedReader reader;
        String line;
        try {
            reader = new BufferedReader(new FileReader("actualSchema.sql"));

            while ((line = reader.readLine()) != null) {
                builder.append(line);
                if (line.contains(";")) {
                    allQueries.add(builder.toString());
                    builder.setLength(0);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<String> insertQueries = allQueries.stream().filter(query -> query.contains("INSERT")).toList();
        List<String> joinQueries = new ArrayList<>();


        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter("data.sql");
            for (String query : insertQueries) {
                if (!(query.contains("BUILDING_FLOORS") || query.contains("FLOOR_ROOMS") ||
                        query.contains("MEASUREMENT_MAIN_MEASUREMENT_ENTRIES") || query.contains("ROOM_MEASUREMENT_MAINS")
                        || (query.contains("PDF"))
                )) {
                    fileWriter.write(query + System.lineSeparator());
                } else if ((query.contains("PDF"))) {
                } else {
                    joinQueries.add(query);
                }
            }
            for (String joinQuery : joinQueries) {
                fileWriter.write(joinQuery + System.lineSeparator());
            }

            fileWriter.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

