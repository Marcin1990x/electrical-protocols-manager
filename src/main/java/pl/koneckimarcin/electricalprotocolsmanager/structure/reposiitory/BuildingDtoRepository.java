package pl.koneckimarcin.electricalprotocolsmanager.structure.reposiitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.koneckimarcin.electricalprotocolsmanager.structure.model.BuildingDto;

@Repository
public interface BuildingDtoRepository extends JpaRepository<BuildingDto, Integer> {
}
