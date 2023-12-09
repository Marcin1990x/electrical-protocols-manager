package pl.koneckimarcin.electricalprotocolsmanager.pdf;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.storage.PdfEntity;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.storage.PdfRepository;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class MainPdfService {

    @Autowired
    PdfRepository pdfRepository;

    public void saveToFile(String fileName) {

        final String destinationPdfPath = "pdf\\" + fileName + ".pdf";

        byte[] pdfData = pdfRepository.findAll().stream().findFirst().get().getData();

        File outputFile = new File(destinationPdfPath);
        try {
            FileOutputStream outputStream = new FileOutputStream(outputFile);
            outputStream.write(pdfData);
            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException("Can not save the pdf file. " + e.getMessage());
        }
    }

    public void saveFileToDb(PDDocument doc) {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        try {
            doc.save(stream);
            pdfRepository.deleteAll();
            PdfEntity createdPdf = new PdfEntity("createdPdf", stream.toByteArray());
            pdfRepository.save(createdPdf);
        } catch (IOException e) {
            throw new RuntimeException("Can not create pdf file. " + e.getMessage());
        }
    }
}
