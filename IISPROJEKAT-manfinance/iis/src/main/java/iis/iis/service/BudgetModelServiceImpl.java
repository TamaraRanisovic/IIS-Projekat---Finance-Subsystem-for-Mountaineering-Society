package iis.iis.service;

import iis.iis.entity.BudgetModel;
import iis.iis.entity.Income;
import iis.iis.repository.BudgetModelRepository;
import iis.iis.repository.IncomeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BudgetModelServiceImpl implements BudgetModelService {


    private final BudgetModelRepository budgetModelRepository;


    public BudgetModelServiceImpl(BudgetModelRepository budgetModelRepository) {
        this.budgetModelRepository = budgetModelRepository;

    }

    @Override
    public BudgetModel createBudgetModel(BudgetModel budgetModel) throws Exception {
        BudgetModel newBudgetModel = this.budgetModelRepository.save(budgetModel);
        return newBudgetModel;
    }

    @Override
    public List<BudgetModel> getAllBudgetModels(String role) throws Exception{
        if(!role.equals("MANFINANCE")){
            throw new Exception ("Nemate pristup ovim podacima!");

        }

        List<BudgetModel> allBudgetModels = this.budgetModelRepository.findAll();

        List<BudgetModel> budgetModels = new ArrayList<>();

        for(BudgetModel budgetModel: allBudgetModels){
            budgetModels.add(budgetModel);
        }

        return budgetModels;
    }

    public BudgetModel getBudgetModelByName(String budgetModelName) {
        return budgetModelRepository.findByName(budgetModelName);
    }


}