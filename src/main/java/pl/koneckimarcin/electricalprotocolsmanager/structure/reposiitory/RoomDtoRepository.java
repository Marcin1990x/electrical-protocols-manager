package pl.koneckimarcin.electricalprotocolsmanager.structure.reposiitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.koneckimarcin.electricalprotocolsmanager.structure.model.RoomDto;

@Repository
public interface RoomDtoRepository extends JpaRepository<RoomDto, Integer> {
}
