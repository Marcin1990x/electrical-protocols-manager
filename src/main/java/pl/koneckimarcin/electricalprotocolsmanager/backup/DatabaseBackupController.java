package pl.koneckimarcin.electricalprotocolsmanager.backup;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@RestController
public class DatabaseBackupController {

    @Autowired
    private DatabaseBackupService service;

    @GetMapping("dbBackup")
    public void dumpTables() {
        service.dumpTables();
    }

    @GetMapping("createSqlFile")
    public void createSqlFile() {

        service.createQueries();
    }
    @GetMapping("test")
    public void loadOnStartup() throws SQLException, FileNotFoundException {

        String dbUrl = "jdbc:h2:mem:test";
        Connection connection = DriverManager.getConnection(dbUrl, "sa", "");
        ScriptRunner sr = new ScriptRunner(connection);
        Reader reader = new BufferedReader(new FileReader("data.sql"));
        sr.runScript(reader);
    }
}
