package iis.iis.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import iis.iis.enums.IncomeCategory;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class PlannedIncome {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_plan_income;

    @Column
    private String description;

    @Column
    private String period;

    @Column
    private String priority;

    @Column
    private float base_price;

    @Column
    private Integer quantity;

    @Enumerated(EnumType.STRING)
    private IncomeCategory incomeCategory;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "budget_model_id")
    private BudgetModel budgetModel;

    public PlannedIncome() {
    }

    public PlannedIncome(Long id_plan_income, String description, String period, String priority, float base_price, Integer quantity, IncomeCategory incomeCategory, BudgetModel budgetModel) {
        this.id_plan_income = id_plan_income;
        this.description = description;
        this.period = period;
        this.priority = priority;
        this.base_price = base_price;
        this.quantity = quantity;
        this.incomeCategory = incomeCategory;
        this.budgetModel = budgetModel;
    }

    public PlannedIncome(String description, String period, String priority, float base_price, Integer quantity, IncomeCategory incomeCategory, BudgetModel budgetModel) {
        this.description = description;
        this.period = period;
        this.priority = priority;
        this.base_price = base_price;
        this.quantity = quantity;
        this.incomeCategory = incomeCategory;
        this.budgetModel = budgetModel;
    }

    public Long getId_plan_income() {
        return id_plan_income;
    }

    public void setId_plan_income(Long id_plan_income) {
        this.id_plan_income = id_plan_income;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public float getBase_price() {
        return base_price;
    }

    public void setBase_price(float base_price) {
        this.base_price = base_price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public IncomeCategory getIncomeCategory() {
        return incomeCategory;
    }

    public void setIncomeCategory(IncomeCategory incomeCategory) {
        this.incomeCategory = incomeCategory;
    }


    public BudgetModel getBudgetModel() {
        return budgetModel;
    }

    public void setBudgetModel(BudgetModel budgetModel) {
        this.budgetModel = budgetModel;
    }
}