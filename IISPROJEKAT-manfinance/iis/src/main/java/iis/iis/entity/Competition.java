package iis.iis.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;

import java.time.LocalDateTime;

@Entity
public class Competition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String type;

    @Column
    private String description;

    @Column
    private String location;

    @Column
    private LocalDateTime dateTime;

    @Column
    private int numberAvailable;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int getNumberAvailable() {
        return numberAvailable;
    }

    public void setNumberAvailable(int numberAvailable) {
        this.numberAvailable = numberAvailable;
    }

    public Pricelist getPricelist() {
        return pricelist;
    }

    public void setPricelist(Pricelist pricelist) {
        this.pricelist = pricelist;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public Reward getReward() {
        return reward;
    }

    public void setReward(Reward reward) {
        this.reward = reward;
    }

 /*   public Person getLeader() {
        return leader;
    }

    public void setLeader(Person leader) {
        this.leader = leader;
    }*/

    public Competition() {
    }

    @ManyToOne(fetch = FetchType.EAGER)
    private Pricelist pricelist;

    @ManyToOne(fetch = FetchType.EAGER)
    private Condition condition;

    @ManyToOne(fetch = FetchType.EAGER)
    private Reward reward;

   //@ManyToOne(fetch = FetchType.EAGER)
   //private Person leader;

    public Competition(Long id, String type, String description, String location, LocalDateTime dateTime, int numberAvailable) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.location = location;
        this.dateTime = dateTime;
        this.numberAvailable = numberAvailable;
        /*this.pricelist = pricelist;
        this.condition = condition;
        this.reward = reward;*/
    }
}