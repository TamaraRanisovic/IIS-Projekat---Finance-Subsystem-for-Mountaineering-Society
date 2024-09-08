package iis.iis.repository;

import iis.iis.entity.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import iis.iis.entity.Competition;
import org.springframework.stereotype.Repository;

public interface CompetitionRepository extends JpaRepository<Competition,Long> {

   /* @Query("select s from Competition s where s.id = ?1")
    Competition pronadjipoid(Long l);*/
}
