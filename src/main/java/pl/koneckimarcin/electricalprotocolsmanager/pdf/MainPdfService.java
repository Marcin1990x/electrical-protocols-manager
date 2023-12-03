package pl.koneckimarcin.electricalprotocolsmanager.pdf;

import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;

@Service
public class MainPdfService {

    public void copyFile(String fileName, String sourcePdfPath) {

        final String destinationPdfPath = "pdf\\"+ fileName + ".pdf";

        try {
            FileCopyUtils.copy(new File(sourcePdfPath), new File(destinationPdfPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
