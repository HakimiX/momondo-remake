/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * This class holds reservation data
 * @author 
 */
@Entity
@Table(name="reservation")
public class Reservation implements Comparable<Reservation>{
    
    @Id
    @GeneratedValue
    private long id;
    private double price;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "reservation",fetch = FetchType.EAGER)
    private List<Passenger> passengers;
    @OneToOne(cascade = CascadeType.ALL)
    private FlightInstance instance;
    
    @JsonIgnore
    @OneToOne
    private User user;

    @JsonIgnore
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public FlightInstance getInstance() {
        return instance;
    }

    public void setInstance(FlightInstance instance) {
        this.instance = instance;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id=" + id + ", price=" + price + ", passengers=" + passengers + ", instance=" + instance + '}';
    }

    @Override
    public int compareTo(Reservation o) {
        if(o.getId() == this.id){
            return 0;
        }
        
        if(o.getId()>this.id){
            return 1;
        }else{
            return 0;
        }
    }
    
    

}
