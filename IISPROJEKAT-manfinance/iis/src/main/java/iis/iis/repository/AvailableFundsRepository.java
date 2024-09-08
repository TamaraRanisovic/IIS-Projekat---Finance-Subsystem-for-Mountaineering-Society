package iis.iis.repository;

import iis.iis.entity.AvailableFunds;
import iis.iis.entity.Income;
import iis.iis.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AvailableFundsRepository extends JpaRepository<AvailableFunds,Long> {


}
