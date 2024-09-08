package iis.iis.entity;

import iis.iis.enums.IncomeCategory;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_income;

    @Column
    private String description;

    @Column
    private float amount;

    @Column
    private LocalDateTime date;

    @Column
    private String incomeSource;

    @Enumerated(EnumType.STRING)
    private IncomeCategory incomeCategory;

    public Income() {
    }

    public Income(Long id_income, String description, float amount, LocalDateTime date, String incomeSource, IncomeCategory incomeCategory) {
        this.id_income = id_income;
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.incomeSource = incomeSource;
        this.incomeCategory = incomeCategory;
    }

    public Income(String description, float amount, LocalDateTime date, String incomeSource, IncomeCategory incomeCategory) {
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.incomeSource = incomeSource;
        this.incomeCategory = incomeCategory;
    }

    public Long getId_income() {
        return id_income;
    }

    public void setId_income(Long id_income) {
        this.id_income = id_income;
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

    public String getIncomeSource() {
        return incomeSource;
    }

    public void setIncomeSource(String incomeSource) {
        this.incomeSource = incomeSource;
    }

    public IncomeCategory getIncomeCategory() {
        return incomeCategory;
    }

    public void setIncomeCategory(IncomeCategory incomeCategory) {
        this.incomeCategory = incomeCategory;
    }
}
