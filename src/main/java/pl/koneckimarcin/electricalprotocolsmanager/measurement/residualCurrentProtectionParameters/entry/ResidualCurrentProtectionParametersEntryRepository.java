package pl.koneckimarcin.electricalprotocolsmanager.measurement.residualCurrentProtectionParameters.entry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResidualCurrentProtectionParametersEntryRepository
        extends JpaRepository<ResidualCurrentProtectionParametersEntry, Integer> {
}
