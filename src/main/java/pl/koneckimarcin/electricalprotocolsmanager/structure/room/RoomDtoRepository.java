package pl.koneckimarcin.electricalprotocolsmanager.structure.room;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomDtoRepository extends JpaRepository<RoomDto, Integer> {
}
