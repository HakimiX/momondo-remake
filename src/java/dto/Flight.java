package dto;


/**
 * This is the class which holds flight data
 */

public class Flight {

    private String flightId;
    private String date;
    private int numberOfSeats;
    private Double totalPrice;
    private int travelTime;
    private String destination;
    private String origin;

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(int travelTime) {
        this.travelTime = travelTime;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flightId='" + flightId + '\'' +
                ", date=" + date +
                ", numberOfSeats=" + numberOfSeats +
                ", totalPrice=" + totalPrice +
                ", travelTime=" + travelTime +
                ", destination='" + destination + '\'' +
                ", origin='" + origin + '\'' +
                '}';
    }
}
