package iis.iis.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;


@Entity
public class BudgetModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_budget_model;

    @Column
    private String period;

    @Column
    private float desired_profit;

    @Column
    private float expected_profit;

    @Column
    private String name;

    @Column
    private LocalDateTime start_date;

    @JsonManagedReference
    @OneToMany(mappedBy = "budgetModel", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PlannedIncome> plannedIncomes;


    @JsonManagedReference
    @OneToMany(mappedBy = "budgetModel", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PlannedExpense> plannedExpenses;


    public BudgetModel() {
    }

    public BudgetModel(Long id_budget_model, String period, float desired_profit, float expected_profit, String name, LocalDateTime start_date, Set<PlannedIncome> plannedIncomes, Set<PlannedExpense> plannedExpenses) {
        this.id_budget_model = id_budget_model;
        this.period = period;
        this.desired_profit = desired_profit;
        this.expected_profit = expected_profit;
        this.name = name;
        this.start_date = start_date;
        this.plannedIncomes = plannedIncomes;
        this.plannedExpenses = plannedExpenses;
    }

    public BudgetModel(String period, float desired_profit, float expected_profit, String name, LocalDateTime start_date, Set<PlannedIncome> plannedIncomes, Set<PlannedExpense> plannedExpenses) {
        this.period = period;
        this.desired_profit = desired_profit;
        this.expected_profit = expected_profit;
        this.name = name;
        this.start_date = start_date;
        this.plannedIncomes = plannedIncomes;
        this.plannedExpenses = plannedExpenses;
    }

    public Long getId_budget_model() {
        return id_budget_model;
    }

    public void setId_budget_model(Long id_budget_model) {
        this.id_budget_model = id_budget_model;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public float getDesired_profit() {
        return desired_profit;
    }

    public void setDesired_profit(float desired_profit) {
        this.desired_profit = desired_profit;
    }

    public float getExpected_profit() {
        return expected_profit;
    }

    public void setExpected_profit(float expected_profit) {
        this.expected_profit = expected_profit;
    }

    public Set<PlannedIncome> getPlannedIncomes() {
        return plannedIncomes;
    }

    public void setPlannedIncomes(Set<PlannedIncome> plannedIncomes) {
        this.plannedIncomes = plannedIncomes;
    }

    public Set<PlannedExpense> getPlannedExpenses() {
        return plannedExpenses;
    }

    public void setPlannedExpenses(Set<PlannedExpense> plannedExpenses) {
        this.plannedExpenses = plannedExpenses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDateTime start_date) {
        this.start_date = start_date;
    }
}