package pl.koneckimarcin.electricalprotocolsmanager.utilities.electrician;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElectricianRepository extends JpaRepository<Electrician, Integer> {
}
