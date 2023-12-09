package pl.koneckimarcin.electricalprotocolsmanager.pdf.storage;

import org.springframework.web.multipart.MultipartFile;

public interface PdfFileService {

    PdfEntity loadFile();
    void addFile(MultipartFile file);
}
