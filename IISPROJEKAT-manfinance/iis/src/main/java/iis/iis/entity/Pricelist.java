package iis.iis.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Pricelist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private double earlyBird;

    @Column
    private double regular;

    @Column
    private double vip;

    @OneToMany(mappedBy ="pricelist", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Competition> pricelistCompetitions =  new HashSet<>();

    public Pricelist(Long id, double earlyBird, double regular, double vip) {
        this.id = id;
        this.earlyBird = earlyBird;
        this.regular = regular;
        this.vip = vip;
    }

    public Pricelist() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getEarlyBird() {
        return earlyBird;
    }

    public void setEarlyBird(double earlyBird) {
        this.earlyBird = earlyBird;
    }

    public double getRegular() {
        return regular;
    }

    public void setRegular(double regular) {
        this.regular = regular;
    }

    public double getVip() {
        return vip;
    }

    public void setVip(double vip) {
        this.vip = vip;
    }
}

