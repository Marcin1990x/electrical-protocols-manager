package pl.koneckimarcin.electricalprotocolsmanager.measurement.continuityOfSmallResistancy.entry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContinuityOfSmallResistanceEntryDtoRepository
        extends JpaRepository <ContinuityOfSmallResistanceEntryDto, Integer> {
}
