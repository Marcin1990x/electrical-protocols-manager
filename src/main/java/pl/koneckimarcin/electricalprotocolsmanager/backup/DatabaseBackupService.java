package pl.koneckimarcin.electricalprotocolsmanager.backup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class DatabaseBackupService {

    private final List<String> dontDoBackupFor = List.of(
            "BUILDING_FLOORS",
            "FLOOR_ROOMS",
            "MEASUREMENT_MAIN_MEASUREMENT_ENTRIES",
            "ROOM_MEASUREMENT_MAINS",
            "PDF",
            "SYSTEM_LOB_STREAM");
    private final List<String> dontDoNothing = List.of(
            "PDF",
            "SYSTEM_LOB_STREAM");

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String schemaName = "actualSchema.sql";
    private final String backupSchemaName = "data.sql";

    public void dumpTables() {

        jdbcTemplate.queryForList("SCRIPT TO '" + schemaName + "'");
    }

    public void createFileWithQueries() {

        List<String> allQueries = createQueriesListFromFile();
        List<String> insertQueries = extractInsertQueries(allQueries);
        List<String> joinQueries = new ArrayList<>();

        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(backupSchemaName);
            writeQueriesToFile(insertQueries, joinQueries, fileWriter);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Problem with file: " + backupSchemaName + " " + e.getMessage());
        }
    }

    private boolean isContainingString(String query, List<String> matchers) {

        boolean isContaining = false;

        for (String matcher : matchers) {
            if (query.contains(matcher)) {
                isContaining = true;
                break;
            }
        }
        return isContaining;
    }

    private List<String> createQueriesListFromFile() {

        List<String> queriesList = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(schemaName));
            String line;
            StringBuilder builder = new StringBuilder();

            while ((line = readLine(reader)) != null) {
                addQueryToList(line, queriesList, builder);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Can not find file: " + schemaName + e.getMessage());
        }
        return queriesList;
    }

    private String readLine(BufferedReader reader) {

        try {
            return reader.readLine();
        } catch (IOException e) {
            System.out.println("Problem with reading line in: " + schemaName + e.getMessage());
        }
        return "";
    }

    private void addQueryToList(String line, List<String> queriesList, StringBuilder builder) {

        builder.append(line);
        if (line.contains(";")) {
            queriesList.add(builder.toString());
            builder.setLength(0);
        }
    }
    private List<String> extractInsertQueries(List<String> allQueries) {

        return allQueries.stream().filter(query -> query.contains("INSERT")).toList();
    }
    private void writeQueriesToFile(List<String> insertQueries, List<String> joinQueries, FileWriter fileWriter) {

        for (String query : insertQueries) {
            if (!isContainingString(query, dontDoBackupFor)) {
                //fileWriter.write(query + System.lineSeparator());
                writeQueryToFile(query, fileWriter);
            } else if (isContainingString(query, dontDoNothing)) {
            } else {
                joinQueries.add(query);
            }
        }
        for (String joinQuery : joinQueries) {
            //fileWriter.write(joinQuery + System.lineSeparator());
            writeQueryToFile(joinQuery, fileWriter);
        }
    }
    private void writeQueryToFile(String query, FileWriter fileWriter) {

        try {
            fileWriter.write(query + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Problem with writing query to file: " + backupSchemaName + " " + e.getMessage());
        }
    }
}

