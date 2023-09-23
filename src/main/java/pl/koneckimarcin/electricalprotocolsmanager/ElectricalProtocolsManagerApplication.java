package pl.koneckimarcin.electricalprotocolsmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class ElectricalProtocolsManagerApplication {


    public static void main(String[] args) throws IOException {
        SpringApplication.run(ElectricalProtocolsManagerApplication.class, args);
    }

}
