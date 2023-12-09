package pl.koneckimarcin.electricalprotocolsmanager.pdf.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class PdfFileController {

    @Autowired
    private PdfFileServiceImpl service;

    @GetMapping("/file")
    public byte[] getFile() {

        PdfEntity pdf = service.loadFile();

        return pdf.getData();
    }

    @PostMapping(value = "/file", consumes = "multipart/form-data")
    public void uploadFile(@RequestParam(value = "pdf") MultipartFile file) {

        service.addFile(file);
    }
}
