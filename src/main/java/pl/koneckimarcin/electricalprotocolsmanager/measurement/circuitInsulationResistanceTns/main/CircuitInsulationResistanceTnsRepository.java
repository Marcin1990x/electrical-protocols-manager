package pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTns.main;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CircuitInsulationResistanceTnsRepository
        extends JpaRepository<CircuitInsulationResistanceTns, Integer> {
}
