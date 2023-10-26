package pl.koneckimarcin.electricalprotocolsmanager.measurement.soilResistance.entry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoilResistanceEntryRepository
        extends JpaRepository<SoilResistanceEntry, Integer> {
}
