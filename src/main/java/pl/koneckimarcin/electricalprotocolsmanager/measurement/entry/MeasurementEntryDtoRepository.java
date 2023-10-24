package pl.koneckimarcin.electricalprotocolsmanager.measurement.entry;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MeasurementEntryDtoRepository extends JpaRepository<MeasurementEntryDto, Integer> {
}
