package pl.koneckimarcin.electricalprotocolsmanager.backup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
