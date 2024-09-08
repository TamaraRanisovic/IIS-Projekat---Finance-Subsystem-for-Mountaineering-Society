package iis.iis.entity;

import iis.iis.enums.IncomeCategory;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class AltRace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_alt_race;

    @Column
    private float length;

    @Column
    private Integer difficulty;

    @Column
    private Integer loc_accessibility;

    @Column
    private Integer nature_beauty;

    @Column
    private Integer safety;

    @Column
    private Integer capacity;

    @Column
    private float price;

    public AltRace() {
    }

    public AltRace(Long id_alt_race, float length, Integer difficulty, Integer loc_accessibility, Integer nature_beauty, Integer safety, Integer capacity, float price) {
        this.id_alt_race = id_alt_race;
        this.length = length;
        this.difficulty = difficulty;
        this.loc_accessibility = loc_accessibility;
        this.nature_beauty = nature_beauty;
        this.safety = safety;
        this.capacity = capacity;
        this.price = price;
    }

    public AltRace(float length, Integer difficulty, Integer loc_accessibility, Integer nature_beauty, Integer safety, Integer capacity, float price) {
        this.length = length;
        this.difficulty = difficulty;
        this.loc_accessibility = loc_accessibility;
        this.nature_beauty = nature_beauty;
        this.safety = safety;
        this.capacity = capacity;
        this.price = price;
    }

    public Long getId_alt_race() {
        return id_alt_race;
    }

    public void setId_alt_race(Long id_alt_race) {
        this.id_alt_race = id_alt_race;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    public Integer getLoc_accessibility() {
        return loc_accessibility;
    }

    public void setLoc_accessibility(Integer loc_accessibility) {
        this.loc_accessibility = loc_accessibility;
    }

    public Integer getNature_beauty() {
        return nature_beauty;
    }

    public void setNature_beauty(Integer nature_beauty) {
        this.nature_beauty = nature_beauty;
    }

    public Integer getSafety() {
        return safety;
    }

    public void setSafety(Integer safety) {
        this.safety = safety;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
