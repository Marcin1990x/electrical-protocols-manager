package pl.koneckimarcin.electricalprotocolsmanager.structure.building;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingDtoRepository extends JpaRepository<BuildingDto, Integer> {
}
