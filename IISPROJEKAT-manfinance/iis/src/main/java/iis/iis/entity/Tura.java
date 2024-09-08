package iis.iis.entity;

import java.sql.Time;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Tura {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column 
    private String naziv;

    @Column
    private double kotizacija;

    @Column
    private String tezinaSetnje;

    @Column
    private Integer brojUcesnika;

    @Column
    private Time vremePolaska;

    @Column
    private LocalDateTime rokPrijave;

    @Column
    private LocalDateTime rokUplate;

    public Tura(Long id){
        this.id = id;
    }

}
