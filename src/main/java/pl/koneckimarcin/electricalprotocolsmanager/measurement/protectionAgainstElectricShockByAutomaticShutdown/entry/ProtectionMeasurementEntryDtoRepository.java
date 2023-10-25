package pl.koneckimarcin.electricalprotocolsmanager.measurement.protectionAgainstElectricShockByAutomaticShutdown.entry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProtectionMeasurementEntryDtoRepository
        extends JpaRepository <ProtectionMeasurementEntryDto, Integer> {
}
