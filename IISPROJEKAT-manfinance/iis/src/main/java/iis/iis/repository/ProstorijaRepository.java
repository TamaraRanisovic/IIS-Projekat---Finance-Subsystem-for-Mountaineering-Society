package iis.iis.repository;

import iis.iis.entity.Person;
import iis.iis.entity.Prostorija;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProstorijaRepository extends JpaRepository<Prostorija,Long> {

    @Query("select s from Prostorija s where s.id = ?1")
    Prostorija pronadjipoid(Long l);
}
