package pl.koneckimarcin.electricalprotocolsmanager.measurement.residualCurrentProtectionParameters.main;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResidualCurrentProtectionParametersDtoRepository
        extends JpaRepository<ResidualCurrentProtectionParametersDto, Integer> {
}
