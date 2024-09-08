package iis.iis.entity;

import java.time.LocalDateTime;

public class TerminDTO {

    private LocalDateTime datum;
    private double cena;
    private int brojprijavljenihclanova;
    private int oznaka;
    private String ime;
    private String prezime;
    private String opis;
    private Tiptermina tip;
    private Long id;

    boolean aktivan;


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

    public int getBrojprijavljenihclanova() {
        return brojprijavljenihclanova;
    }

    public void setBrojprijavljenihclanova(int brojprijavljenihclanova) {
        this.brojprijavljenihclanova = brojprijavljenihclanova;
    }

    public int getOznaka() {
        return oznaka;
    }

    public void setOznaka(int oznaka) {
        this.oznaka = oznaka;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Tiptermina getTip() {
        return tip;
    }

    public void setTip(Tiptermina tip) {
        this.tip = tip;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isAktivan() {
        return aktivan;
    }

    public void setAktivan(boolean aktivan) {
        this.aktivan = aktivan;
    }

    public TerminDTO(LocalDateTime datum, double cena, int brojprijavljenihclanova, int oznaka, String ime, String prezime, String opis, Tiptermina tip, Long id, boolean aktivan) {
        this.datum = datum;
        this.cena = cena;
        this.brojprijavljenihclanova = brojprijavljenihclanova;
        this.oznaka = oznaka;
        this.ime = ime;
        this.prezime = prezime;
        this.opis = opis;
        this.tip = tip;
        this.id = id;
        this.aktivan = aktivan;
    }


    public TerminDTO() {
    }
}
