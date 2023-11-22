package pl.koneckimarcin.electricalprotocolsmanager.backup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DatabaseBackupService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void dumpTables() {
        List<Map<String, Object>> tableNames = jdbcTemplate.queryForList("SCRIPT TO 'Testdata.sql'");

    }
}
