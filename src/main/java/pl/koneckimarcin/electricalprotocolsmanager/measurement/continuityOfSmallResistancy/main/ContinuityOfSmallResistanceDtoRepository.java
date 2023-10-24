package pl.koneckimarcin.electricalprotocolsmanager.measurement.continuityOfSmallResistancy.main;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContinuityOfSmallResistanceDtoRepository
        extends JpaRepository<ContinuityOfSmallResistanceDto, Integer> {
}
