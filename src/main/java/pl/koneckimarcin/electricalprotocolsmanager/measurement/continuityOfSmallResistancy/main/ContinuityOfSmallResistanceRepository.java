package pl.koneckimarcin.electricalprotocolsmanager.measurement.continuityOfSmallResistancy.main;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContinuityOfSmallResistanceRepository
        extends JpaRepository<ContinuityOfSmallResistance, Integer> {
}
