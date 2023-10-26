package pl.koneckimarcin.electricalprotocolsmanager.measurement.protectionAgainstElectricShockByAutomaticShutdown.main;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProtectionAgainstElectricShockByAutomaticShutdownRepository
        extends JpaRepository<ProtectionAgainstElectricShockByAutomaticShutdown, Integer> {
}
