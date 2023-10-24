package pl.koneckimarcin.electricalprotocolsmanager.measurement.main;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementMainDtoRepository extends JpaRepository<MeasurementMainDto, Integer> {
}
