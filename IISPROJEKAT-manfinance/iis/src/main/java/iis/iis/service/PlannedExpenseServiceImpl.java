package iis.iis.service;

import iis.iis.entity.PlannedExpense;
import iis.iis.entity.PlannedIncome;
import iis.iis.repository.PlannedExpenseRepository;
import iis.iis.repository.PlannedIncomeRepository;
import org.springframework.stereotype.Service;

@Service
public class PlannedExpenseServiceImpl implements PlannedExpenseService {

    private final PlannedExpenseRepository plannedExpenseRepositorya;


    public PlannedExpenseServiceImpl(PlannedExpenseRepository plannedExpenseRepositorya) {
        this.plannedExpenseRepositorya = plannedExpenseRepositorya;

    }

    @Override
    public PlannedExpense createPlannedExpense(PlannedExpense plannedExpense) throws Exception {
        PlannedExpense newPlannedExpense = this.plannedExpenseRepositorya.save(plannedExpense);
        return newPlannedExpense;
    }
}
