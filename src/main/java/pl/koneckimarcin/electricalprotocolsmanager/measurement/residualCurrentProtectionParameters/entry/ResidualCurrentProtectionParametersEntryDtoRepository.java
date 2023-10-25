package pl.koneckimarcin.electricalprotocolsmanager.measurement.residualCurrentProtectionParameters.entry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface ResidualCurrentProtectionParametersEntryDtoRepository
        extends JpaRepository<ResidualCurrentProtectionParametersEntryDto, Integer> {
}
