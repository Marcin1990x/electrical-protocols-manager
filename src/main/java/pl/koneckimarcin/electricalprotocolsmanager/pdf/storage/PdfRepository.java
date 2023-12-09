package pl.koneckimarcin.electricalprotocolsmanager.pdf.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PdfRepository extends JpaRepository <PdfEntity, Integer> {
}
