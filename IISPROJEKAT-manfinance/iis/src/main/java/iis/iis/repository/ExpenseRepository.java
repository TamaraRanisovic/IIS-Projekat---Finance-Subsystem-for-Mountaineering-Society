package iis.iis.repository;

import iis.iis.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense,Long> {
    List<Expense> findByDateBetween(LocalDateTime startDate, LocalDateTime endDate);

}
