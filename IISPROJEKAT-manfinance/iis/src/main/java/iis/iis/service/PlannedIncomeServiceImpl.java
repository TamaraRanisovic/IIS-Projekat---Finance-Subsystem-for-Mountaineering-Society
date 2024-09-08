package iis.iis.service;

import iis.iis.entity.BudgetModel;
import iis.iis.entity.PlannedIncome;
import iis.iis.repository.BudgetModelRepository;
import iis.iis.repository.PlannedIncomeRepository;
import org.springframework.stereotype.Service;

@Service
public class PlannedIncomeServiceImpl implements PlannedIncomeService {

    private final PlannedIncomeRepository plannedIncomeRepository;


    public PlannedIncomeServiceImpl(PlannedIncomeRepository plannedIncomeRepository) {
        this.plannedIncomeRepository = plannedIncomeRepository;

    }

    @Override
    public PlannedIncome createPlannedIncome(PlannedIncome plannedIncome) throws Exception {
        PlannedIncome newPlannedIncome = this.plannedIncomeRepository.save(plannedIncome);
        return newPlannedIncome;
    }
}
