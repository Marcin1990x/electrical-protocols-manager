package pl.koneckimarcin.electricalprotocolsmanager.pdf.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class PdfFileServiceImpl implements PdfFileService {

    @Autowired
    PdfRepository repository;

    @Override
    public PdfEntity loadFile() {
        return repository.findAll().stream().findFirst().get();
    }

    @Override
    public void addFile(MultipartFile file) {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            PdfEntity pdfFile = new PdfEntity(fileName, file.getBytes());
            repository.save(pdfFile);
        } catch (IOException e) {
            throw new RuntimeException("Could not save file: " + fileName);
        }
    }

    @Override
    public void deleteAll() {

        repository.deleteAll();
    }
}
