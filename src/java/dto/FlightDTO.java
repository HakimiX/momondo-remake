/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * This is the class which holds Flight data
 * @author 
 */
@Entity
@Table(name="flight")
public class FlightDTO {
    
    @Id
    private String flightNumber;
    private int numberOfSeats;
    @OneToMany(mappedBy = "flight")
    private List<FlightInstance> flights;
    @JsonIgnore
    @OneToOne
    private Airline airline;

    @JsonIgnore
    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }
    
    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public List<FlightInstance> getFlights() {
        return flights;
    }

    public void setFlights(List<FlightInstance> flights) {
        this.flights = flights;
    }

    @Override
    public String toString() {
        return "FlightDTO{" + "flightNumber=" + flightNumber + ", numberOfSeats=" + numberOfSeats + ", flights=" + flights + '}';
    }
}
