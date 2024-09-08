package iis.iis.repository;

import iis.iis.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<Person,Long> {

    Person findByEmailAndPassword(String emailadresa, String lozinka);

    boolean existsPersonByEmailOrPassword(String email, String password);

    @Query("select s from Person s where s.id = ?1")
    Person pronadjipoid(Long l);

}
