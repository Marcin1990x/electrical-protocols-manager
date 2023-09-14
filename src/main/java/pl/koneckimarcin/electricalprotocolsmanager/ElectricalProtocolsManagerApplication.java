package pl.koneckimarcin.electricalprotocolsmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.PdfGenerator;

import java.io.IOException;

@SpringBootApplication
public class ElectricalProtocolsManagerApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(ElectricalProtocolsManagerApplication.class, args);

		Run.run();
	}

}
