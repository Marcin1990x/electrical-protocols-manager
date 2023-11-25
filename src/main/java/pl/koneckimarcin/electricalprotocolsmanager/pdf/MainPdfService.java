package pl.koneckimarcin.electricalprotocolsmanager.pdf;

import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;

@Service
public class MainPdfService {

    private final String sourcePdfPath = "F:\\Programista\\Pomiary elektryczne\\electrical-protocols-manager-ui\\electrical-protocols-manager-ui\\src\\test.pdf";
    private final String destinationPdfPath = "C:\\Users\\Dom\\Desktop\\lol.pdf"; // folder z którego odpali się JAR

    public void copyFile() {

        try {
            FileCopyUtils.copy(new File(sourcePdfPath), new File(destinationPdfPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
