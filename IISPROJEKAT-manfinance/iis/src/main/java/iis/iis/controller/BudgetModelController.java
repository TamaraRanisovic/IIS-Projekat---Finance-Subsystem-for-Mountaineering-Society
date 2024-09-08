package iis.iis.controller;

import iis.iis.entity.BudgetModel;
import iis.iis.entity.Income;
import iis.iis.entity.PlannedExpense;
import iis.iis.entity.PlannedIncome;
import iis.iis.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/budgetmodel")
public class BudgetModelController {


    private final BudgetModelService budgetModelService;
    private final PlannedIncomeService plannedIncomeService;
    private final PlannedExpenseService plannedExpenseService;

    @Autowired
    public BudgetModelController(BudgetModelService budgetModelService, PlannedIncomeService plannedIncomeService, PlannedExpenseService plannedExpenseService) {
        this.budgetModelService = budgetModelService;
        this.plannedIncomeService = plannedIncomeService;
        this.plannedExpenseService = plannedExpenseService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE, value = "/add")
    public ResponseEntity<BudgetModel> createBudgetModel(@RequestBody BudgetModel budgetModel, @RequestParam(value = "role")  String role) throws Exception {

        BudgetModel newBudgetModel = budgetModelService.createBudgetModel(budgetModel);
        //plannedIncomeService.increaseAvailableFunds(newIncome.getAmount(), role);
        return new ResponseEntity<>(newBudgetModel, HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/all")
    public ResponseEntity<List<BudgetModel>> getAllBudgetModels(@RequestParam(value = "role")  String role) throws Exception{

        List<BudgetModel> allBudgetModels  = this.budgetModelService.getAllBudgetModels(role);

        return new ResponseEntity<>(allBudgetModels, HttpStatus.OK);

    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/{budgetModelName}/planned-incomes")
    public ResponseEntity<Set<PlannedIncome>> getAllPlannedIncomesByBudgetModelName(@PathVariable String budgetModelName) {
        try {
            BudgetModel budgetModel = budgetModelService.getBudgetModelByName(budgetModelName);
            if (budgetModel == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            Set<PlannedIncome> plannedIncomes = budgetModel.getPlannedIncomes();
            return new ResponseEntity<>(plannedIncomes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/{budgetModelName}/planned-expenses")
    public ResponseEntity<Set<PlannedExpense>> getAllPlannedExpensesByBudgetModelName(@PathVariable String budgetModelName) {
        try {
            BudgetModel budgetModel = budgetModelService.getBudgetModelByName(budgetModelName);
            if (budgetModel == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            Set<PlannedExpense> plannedExpenses = budgetModel.getPlannedExpenses();
            return new ResponseEntity<>(plannedExpenses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/{budgetModelName}")
    public ResponseEntity<BudgetModel> getBudgetModelByName(@PathVariable String budgetModelName) {
        try {
            BudgetModel budgetModel = budgetModelService.getBudgetModelByName(budgetModelName);
            if (budgetModel == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(budgetModel, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}