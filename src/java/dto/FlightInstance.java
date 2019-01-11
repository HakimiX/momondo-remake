/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * This class holds flight instance data
 * @author 
 */
@Entity
@Table(name="flightinstance")
public class FlightInstance {
    
    @Id
    @GeneratedValue
    private int id;
    @Temporal(TemporalType.TIME)
    private Date departureTime;
    @Temporal(TemporalType.DATE)
    private Calendar departureDate;
    private int flightTime;
    
    @JsonIgnore
    @OneToOne
    private FlightDTO flight;
    private int availableSeats;
    private double price;
    @ManyToMany
    private List<Airport> fliesTo;
    @ManyToMany
    private List<Airport> fliesFrom;
    
    @OneToOne
    private Airport destination;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Calendar getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Calendar departureDate) {
        this.departureDate = departureDate;
    }

    public int getFlightTime() {
        return flightTime;
    }

    public void setFlightTime(int flightTime) {
        this.flightTime = flightTime;
    }

    @JsonIgnore
    public FlightDTO getFlight() {
        return flight;
    }

    public void setFlight(FlightDTO flight) {
        this.flight = flight;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Airport> getFliesTo() {
        return fliesTo;
    }

    public void setFliesTo(List<Airport> fliesTo) {
        this.fliesTo = fliesTo;
    }

    public List<Airport> getFliesFrom() {
        return fliesFrom;
    }

    public void setFliesFrom(List<Airport> fliesFrom) {
        this.fliesFrom = fliesFrom;
    }

    public Airport getDestination() {
        return destination;
    }

    public void setDestination(Airport destination) {
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "FlightInstance{" + "id=" + id + ", departureTime=" + departureTime + ", departureDate=" + departureDate + ", flightTime=" + flightTime + ", availableSeats=" + availableSeats + ", price=" + price + ", fliesTo=" + fliesTo + ", fliesFrom=" + fliesFrom + ", destination=" + destination + '}';
    }

}
