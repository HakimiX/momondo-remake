package dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * This is the class which holds reservation request
 */
@XmlRootElement
public class ReservationRequest {

    private String flightID;
    private int numberOfSeats;
    private String ReserveeName;
    private String ReservePhone;
    private String ReserveEmail;
    private List<Passenger> Passengers;

    public String getFlightID() {
        return flightID;
    }

    public void setFlightID(String flightID) {
        this.flightID = flightID;
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

    public String getReservePhone() {
        return ReservePhone;
    }

    public void setReservePhone(String reservePhone) {
        ReservePhone = reservePhone;
    }

    public String getReserveEmail() {
        return ReserveEmail;
    }

    public void setReserveEmail(String reserveEmail) {
        ReserveEmail = reserveEmail;
    }

    public List<Passenger> getPassengers() {
        return Passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        Passengers = passengers;
    }

    @Override
    public String toString() {
        return "ReservationRequest{" +
                "flightID='" + flightID + '\'' +
                ", numberOfSeats=" + numberOfSeats +
                ", ReserveeName='" + ReserveeName + '\'' +
                ", ReservePhone='" + ReservePhone + '\'' +
                ", ReserveEmail='" + ReserveEmail + '\'' +
                ", Passengers=" + Passengers +
                '}';
    }
}
