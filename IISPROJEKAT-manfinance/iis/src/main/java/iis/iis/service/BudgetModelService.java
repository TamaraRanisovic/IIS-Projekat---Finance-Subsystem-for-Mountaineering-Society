package iis.iis.service;

import iis.iis.entity.BudgetModel;
import iis.iis.entity.Income;

import java.util.List;

public interface BudgetModelService {

    BudgetModel createBudgetModel(BudgetModel budgetModel) throws Exception;
    List<BudgetModel> getAllBudgetModels(String role) throws Exception;
    BudgetModel getBudgetModelByName(String budgetModelName);

}
