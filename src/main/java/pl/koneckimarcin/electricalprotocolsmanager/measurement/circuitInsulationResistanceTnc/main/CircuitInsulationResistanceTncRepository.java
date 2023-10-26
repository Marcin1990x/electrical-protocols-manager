package pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTnc.main;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CircuitInsulationResistanceTncRepository
        extends JpaRepository<CircuitInsulationResistanceTnc, Integer> {
}
