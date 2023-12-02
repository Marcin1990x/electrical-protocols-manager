package pl.koneckimarcin.electricalprotocolsmanager.pdf;

import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;

@Service
public class MainPdfService {

    private final String sourcePdfPath = "F:\\Programista\\Pomiary elektryczne\\electrical-protocols-manager-ui\\electrical-protocols-manager-ui\\src\\test.pdf";

    public void copyFile(String fileName) {

        final String destinationPdfPath = "pdf\\"+ fileName + ".pdf"; // folder z którego odpali się JAR

        try {
            FileCopyUtils.copy(new File(sourcePdfPath), new File(destinationPdfPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
