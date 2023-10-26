package pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTns.entry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CircuitInsulationResistanceTnsEntryRepository
        extends JpaRepository<CircuitInsulationResistanceTnsEntry, Integer> {
}
