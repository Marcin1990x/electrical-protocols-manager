package pl.koneckimarcin.electricalprotocolsmanager.structure.reposiitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.koneckimarcin.electricalprotocolsmanager.structure.model.FloorDto;

@Repository
public interface FloorDtoRepository extends JpaRepository<FloorDto, Integer> {
}
