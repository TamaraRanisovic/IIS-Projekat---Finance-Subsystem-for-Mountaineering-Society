package iis.iis.service;

import iis.iis.entity.PlannedExpense;
import iis.iis.entity.PlannedIncome;

public interface PlannedExpenseService {

    PlannedExpense createPlannedExpense(PlannedExpense plannedExpense) throws Exception;

}
