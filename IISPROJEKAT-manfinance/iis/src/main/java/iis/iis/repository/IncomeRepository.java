package iis.iis.repository;

import iis.iis.entity.Income;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface IncomeRepository extends JpaRepository<Income,Long> {
    List<Income> findByDateBetween(LocalDateTime startDate, LocalDateTime endDate);

}
