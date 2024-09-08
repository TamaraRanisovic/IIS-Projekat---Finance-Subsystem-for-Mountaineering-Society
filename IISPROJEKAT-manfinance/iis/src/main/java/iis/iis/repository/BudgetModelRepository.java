package iis.iis.repository;

import iis.iis.entity.BudgetModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetModelRepository extends JpaRepository<BudgetModel,Long> {
    BudgetModel findByName(String name);

}