package iis.iis.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Termin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDateTime datum;

    @Column
    private double cena;

    @Column
    private String opis;

    @Column
    private boolean aktivan;

    @Column
    private int brojprijavljenih;

    @Enumerated(EnumType.STRING)
    private Tiptermina tiptermina;

    public Termin(Long id, LocalDateTime datum, double cena, String opis) {
        this.id = id;
        this.datum = datum;
        this.cena = cena;
        this.opis = opis;
    }

    public Termin() {
    }


    @ManyToOne(fetch = FetchType.EAGER)
    private Prostorija prostorija;

    @ManyToOne(fetch = FetchType.EAGER)
    private Person profesor;

    @ManyToMany(mappedBy = "termin1")
    private Set<Person> pohadjaci = new HashSet<>();



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDatum() {
        return datum;
    }

    public void setDatum(LocalDateTime datum) {
        this.datum = datum;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Tiptermina getTiptermina() {
        return tiptermina;
    }

    public void setTiptermina(Tiptermina tiptermina) {
        this.tiptermina = tiptermina;
    }

    public Prostorija getProstorija() {
        return prostorija;
    }

    public void setProstorija(Prostorija prostorija) {
        this.prostorija = prostorija;
    }

    public boolean isAktivan() {
        return aktivan;
    }

    public void setAktivan(boolean aktivan) {
        this.aktivan = aktivan;
    }

    public int getBrojprijavljenih() {
        return brojprijavljenih;
    }

    public void setBrojprijavljenih(int brojprijavljenih) {
        this.brojprijavljenih = brojprijavljenih;
    }

    public Set<Person> getPohadjaci() {
        return pohadjaci;
    }

    public void setPohadjaci(Set<Person> pohadjaci) {
        this.pohadjaci = pohadjaci;
    }

    public Person getProfesor() {
        return profesor;
    }

    public void setProfesor(Person profesor) {
        this.profesor = profesor;
    }
}
