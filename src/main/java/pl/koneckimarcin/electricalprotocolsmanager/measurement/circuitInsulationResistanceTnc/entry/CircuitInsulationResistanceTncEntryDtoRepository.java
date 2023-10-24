package pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTnc.entry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CircuitInsulationResistanceTncEntryDtoRepository
        extends JpaRepository<CircuitInsulationResistanceTncEntryDto, Integer> {
}
