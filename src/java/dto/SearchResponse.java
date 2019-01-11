package dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * This is the response class which contains search result for a request.
 */
@XmlRootElement
public class SearchResponse {

    private String airline;
    private List<Flight> flights;

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    @Override
    public String toString() {
        return "SearchResponse{" +
                "airline='" + airline + '\'' +
                ", flights=" + flights +
                '}';
    }
}
