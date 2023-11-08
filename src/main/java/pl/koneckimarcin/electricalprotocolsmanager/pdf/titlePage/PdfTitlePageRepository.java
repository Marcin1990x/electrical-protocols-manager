package pl.koneckimarcin.electricalprotocolsmanager.pdf.titlePage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PdfTitlePageRepository extends JpaRepository<PdfTitlePage, Integer> {
}
