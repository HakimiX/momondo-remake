package dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * This is the response class for reservation request.
 */
@XmlRootElement
public class ReservationResponse {

    private String flightID;
    private String Origin;
    private String Destination;
    private String Date;
    private int FlightTime;
    private int numberOfSeats;
    private String ReserveeName;
    private List<Passenger> Passengers;

    public String getFlightID() {
        return flightID;
    }

    public void setFlightID(String flightID) {
        this.flightID = flightID;
    }

    public String getOrigin() {
        return Origin;
    }

    public void setOrigin(String origin) {
        Origin = origin;
    }

    public String getDestination() {
        return Destination;
    }

    public void setDestination(String destination) {
        Destination = destination;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public int getFlightTime() {
        return FlightTime;
    }

    public void setFlightTime(int flightTime) {
        FlightTime = flightTime;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public String getReserveeName() {
        return ReserveeName;
    }

    public void setReserveeName(String reserveeName) {
        ReserveeName = reserveeName;
    }

    public List<Passenger> getPassengers() {
        return Passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        Passengers = passengers;
    }

    @Override
    public String toString() {
        return "ReservationResponse{" +
                "flightID='" + flightID + '\'' +
                ", Origin='" + Origin + '\'' +
                ", Destination='" + Destination + '\'' +
                ", Date=" + Date +
                ", FlightTime=" + FlightTime +
                ", numberOfSeats=" + numberOfSeats +
                ", ReserveeName='" + ReserveeName + '\'' +
                ", Passengers=" + Passengers +
                '}';
    }
}
