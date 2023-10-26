package pl.koneckimarcin.electricalprotocolsmanager.measurement.soilResistance.main;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoilResistanceRepository
        extends JpaRepository<SoilResistance, Integer> {
}
