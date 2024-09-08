package iis.iis.entity;

import java.time.LocalDate;
import java.util.Date;

public class PersonDTO {

    private String firstname;
    private String lastname;

    private String phonenumber;
    private String email;
    private String password;
    private LocalDate dateofbirth;


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

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(LocalDate dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public PersonDTO() {
    }

    public PersonDTO(String firstname, String lastname, String phonenumber, String email, String password, LocalDate dateofbirth) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phonenumber = phonenumber;
        this.email = email;
        this.password = password;
        this.dateofbirth = dateofbirth;
    }
}
