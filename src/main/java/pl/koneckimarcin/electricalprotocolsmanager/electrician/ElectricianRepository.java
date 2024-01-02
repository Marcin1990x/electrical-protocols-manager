package pl.koneckimarcin.electricalprotocolsmanager.electrician;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ElectricianRepository extends JpaRepository<Electrician, Integer> {

    public List<Electrician> findByLastName(String lastName);
}
