package iis.iis.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Person {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @Column
    private String password;

    @Column
    private String firstname;
    @Column
    private String lastname;
    @Column
    private LocalDate dateofbirth;
    @Column
    private String email;
    @Column
    private String phonenumber;
    @Enumerated(EnumType.STRING)
    private Role role;



    @OneToMany(mappedBy ="profesor", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set <Termin>  kreiranitermini =  new HashSet<>();

    @ManyToMany
    @JoinTable(name = "rezervisantermin",
            joinColumns = @JoinColumn(name = "clan_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "termin_id", referencedColumnName = "id"))

    private Set<Termin> termin1 = new HashSet<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDate getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(LocalDate dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<Termin> getKreiranitermini() {
        return kreiranitermini;
    }

    public void setKreiranitermini(Set<Termin> kreiranitermini) {
        this.kreiranitermini = kreiranitermini;
    }

    public Set<Termin> getTermin1() {
        return termin1;
    }

    public void setTermin1(Set<Termin> termin1) {
        this.termin1 = termin1;
    }

    public Person(Long id, String password, String firstname, String lastname, LocalDate dateofbirth, String email, String phonenumber) {
        this.id = id;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateofbirth = dateofbirth;
        this.email = email;
        this.phonenumber = phonenumber;
    }


    public Person() {
    }


}
