package pl.koneckimarcin.electricalprotocolsmanager.structure.floor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FloorDtoRepository extends JpaRepository<FloorDto, Integer> {
}
