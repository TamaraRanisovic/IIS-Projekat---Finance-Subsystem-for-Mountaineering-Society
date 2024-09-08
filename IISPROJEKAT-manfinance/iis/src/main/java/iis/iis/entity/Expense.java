package iis.iis.entity;

import iis.iis.enums.ExpenseCategory;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_expense;

    @Column
    private String description;

    @Column
    private float amount;

    @Column
    private LocalDateTime date;

    @Column
    private String expenseSource;

    @Enumerated(EnumType.STRING)
    private ExpenseCategory expenseCategory;

    public Expense() {
    }

    public Expense(Long id_expense, String description, float amount, LocalDateTime date, String expenseSource, ExpenseCategory expenseCategory) {
        this.id_expense = id_expense;
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.expenseSource = expenseSource;
        this.expenseCategory = expenseCategory;
    }

    public Expense(String description, float amount, LocalDateTime date, String expenseSource, ExpenseCategory expenseCategory) {
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.expenseSource = expenseSource;
        this.expenseCategory = expenseCategory;
    }

    public Long getId_expense() {
        return id_expense;
    }

    public void setId_expense(Long id_expense) {
        this.id_expense = id_expense;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getExpenseSource() {
        return expenseSource;
    }

    public void setExpenseSource(String expenseSource) {
        this.expenseSource = expenseSource;
    }

    public ExpenseCategory getExpenseCategory() {
        return expenseCategory;
    }

    public void setExpenseCategory(ExpenseCategory expenseCategory) {
        this.expenseCategory = expenseCategory;
    }
}
