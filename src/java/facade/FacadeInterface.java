/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import com.flightapi.dto.FlightDTO;
import com.flightapi.dto.FlightInstance;
import com.flightapi.dto.Reservation;
import com.flightapi.dto.User;
import java.util.Calendar;
import java.util.List;

/**
 * This is the interface class for db operation
 * @author 
 */
public interface FacadeInterface {
    
    public List<FlightInstance> getFlightInstance(String from, String to, Calendar date, int numberOfSeats);
    public FlightDTO getFlightById(String flightId);
    public void reserveFlight(Reservation reservation);
    public User getUserByEmail(String email);
    public List<Reservation> getAllReservations();
}
