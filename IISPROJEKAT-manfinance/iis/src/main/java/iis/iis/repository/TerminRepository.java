package iis.iis.repository;

import iis.iis.entity.Person;
import iis.iis.entity.Prostorija;
import iis.iis.entity.Termin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TerminRepository extends JpaRepository<Termin,Long> {

    @Query("select s from Termin s where s.id = ?1")
    Termin pronadjipoid(Long l);
}
