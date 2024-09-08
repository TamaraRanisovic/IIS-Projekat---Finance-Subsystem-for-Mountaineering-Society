package iis.iis.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class AvailableFunds {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_available_funds;

    @Column
    private float amount;

    @Column
    private LocalDateTime updateDate;

    public AvailableFunds() {
    }

    public AvailableFunds(Long id_available_funds, float amount, LocalDateTime updateDate) {
        this.id_available_funds = id_available_funds;
        this.amount = amount;
        this.updateDate = updateDate;
    }

    public AvailableFunds(float amount, LocalDateTime updateDate) {
        this.amount = amount;
        this.updateDate = updateDate;
    }

    public Long getId_available_funds() {
        return id_available_funds;
    }

    public void setId_available_funds(Long id_available_funds) {
        this.id_available_funds = id_available_funds;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }
}
