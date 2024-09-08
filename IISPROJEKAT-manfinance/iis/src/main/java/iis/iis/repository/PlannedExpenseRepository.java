package iis.iis.repository;

import iis.iis.entity.PlannedExpense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlannedExpenseRepository extends JpaRepository<PlannedExpense,Long> {

}