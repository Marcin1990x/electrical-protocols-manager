package pl.koneckimarcin.electricalprotocolsmanager;

import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Startup {

    public static void loadOnStartup() {

        String dbUrl = "jdbc:h2:mem:test";
        ScriptRunner sr;
        Connection connection;

        try {
            connection = DriverManager.getConnection(dbUrl, "sa", "");
            sr = new ScriptRunner(connection);
            try {
                Reader reader = new BufferedReader(new FileReader("data.sql"));
                sr.runScript(reader);
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
