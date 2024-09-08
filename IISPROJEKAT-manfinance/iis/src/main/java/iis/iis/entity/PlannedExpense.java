package iis.iis.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import iis.iis.enums.ExpenseCategory;
import iis.iis.enums.IncomeCategory;
import jakarta.persistence.*;

@Entity
public class PlannedExpense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_plan_expense;

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
    private ExpenseCategory expenseCategory;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "budget_model_id")
    private BudgetModel budgetModel;

    public PlannedExpense() {
    }

    public PlannedExpense(Long id_plan_expense, String description, String period, String priority, float base_price, Integer quantity, ExpenseCategory expenseCategory, BudgetModel budgetModel) {
        this.id_plan_expense = id_plan_expense;
        this.description = description;
        this.period = period;
        this.priority = priority;
        this.base_price = base_price;
        this.quantity = quantity;
        this.expenseCategory = expenseCategory;
        this.budgetModel = budgetModel;
    }

    public PlannedExpense(String description, String period, String priority, float base_price, Integer quantity, ExpenseCategory expenseCategory, BudgetModel budgetModel) {
        this.description = description;
        this.period = period;
        this.priority = priority;
        this.base_price = base_price;
        this.quantity = quantity;
        this.expenseCategory = expenseCategory;
        this.budgetModel = budgetModel;
    }

    public Long getId_plan_expense() {
        return id_plan_expense;
    }

    public void setId_plan_expense(Long id_plan_expense) {
        this.id_plan_expense = id_plan_expense;
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

    public ExpenseCategory getExpenseCategory() {
        return expenseCategory;
    }

    public void setExpenseCategory(ExpenseCategory expenseCategory) {
        this.expenseCategory = expenseCategory;
    }

    public BudgetModel getBudgetModel() {
        return budgetModel;
    }

    public void setBudgetModel(BudgetModel budgetModel) {
        this.budgetModel = budgetModel;
    }
}
