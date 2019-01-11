package service;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.flightapi.dto.Flight;
import com.flightapi.dto.FlightDTO;
import com.flightapi.dto.FlightInstance;
import com.flightapi.dto.Passenger;
import com.flightapi.dto.Reservation;
import com.flightapi.dto.ReservationRequest;
import com.flightapi.dto.ReservationResponse;
import com.flightapi.dto.SearchResponse;
import com.flightapi.dto.User;
import com.flightapi.exception.FlightException;
import facade.FacadeController;
import facade.FacadeInterface;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

/**
 * This is the main controller class for all the rest APIs.
 */
@Path("/")
public class AirlineEngineService {

    final static Logger logger = Logger.getLogger(AirlineEngineService.class);

    private FacadeInterface fi = new FacadeController();

    @GET
    @Path("/flightinfo/{from}/{to}/{date}/{numTickets}")
    @Produces(MediaType.APPLICATION_JSON)
    public SearchResponse searchFlightBetweenDestination(@PathParam("from") String from, @PathParam("to") String to, @PathParam("date") String date, @PathParam("numTickets") Integer numTickets) throws FlightException {
        logger.info("Inside searchFlightBetweenDestination");
        if (from != null && to != null && date != null && numTickets != null) {
            Date parsedDate = Util.convertISODate(date);
            if (parsedDate == null) {
                throw new FlightException(Response.Status.BAD_REQUEST, 3, "Invalid parameter date");
            }
            parsedDate.setHours(0);
            parsedDate.setMinutes(0);
            parsedDate.setSeconds(0);
            Calendar c = GregorianCalendar.getInstance();
            c.setTime(parsedDate);
            List<FlightInstance> lsFi = fi.getFlightInstance(from, to, c, numTickets);
            if (lsFi != null && lsFi.size() > 0) {
                return mapResponse(lsFi, numTickets);
            }
            throw new FlightException(Response.Status.NOT_FOUND, 1, "No Flights");
        } else {
            logger.info("Inside searchFlightBetweenDestination invalid parameters");
            throw new FlightException(Response.Status.BAD_REQUEST, 3, "Invalid parameters");
        }
    }

    private SearchResponse mapResponse(List<FlightInstance> lsFi, int numTickets) {
        if (lsFi != null && lsFi.size() > 0) {
            SearchResponse searchResponse = new SearchResponse();
            List<Flight> lsFlights = new ArrayList<>();
            for (FlightInstance fi : lsFi) {
                searchResponse.setAirline(fi.getFlight().getAirline().getName());
                Flight f = mapFlightInstanceToFlight(fi);
                f.setTotalPrice(fi.getPrice() * numTickets);
                lsFlights.add(f);
            }
            searchResponse.setFlights(lsFlights);
            return searchResponse;
        }
        return null;
    }

    private Flight mapFlightInstanceToFlight(FlightInstance fi) {
        Flight f = new Flight();
        Calendar d = fi.getDepartureDate();
        if (fi.getDepartureTime() != null) {
            d.set(Calendar.HOUR_OF_DAY, fi.getDepartureTime().getHours());
            d.set(Calendar.MINUTE, fi.getDepartureTime().getMinutes());
        }
        f.setDate(Util.formatDate(d.getTime()));
        f.setDestination(fi.getDestination().getIataCode());
        f.setFlightId(fi.getFlight().getFlightNumber());
        f.setNumberOfSeats(fi.getAvailableSeats());
        f.setOrigin(fi.getFliesFrom().get(0).getIataCode());

        f.setTravelTime(fi.getFlightTime());
        return f;
    }

    @GET
    @Path("/flightinfo/{from}/{date}/{numTickets}")
    @Produces(MediaType.APPLICATION_JSON)
    public SearchResponse searchFlight(@PathParam("from") String from, @PathParam("date") String date, @PathParam("numTickets") Integer numTickets) throws FlightException {
        logger.info("Inside searchFlight");
        if (from != null && date != null && numTickets != null) {
            Date parsedDate = Util.convertISODate(date);
            if (parsedDate == null) {
                throw new FlightException(Response.Status.BAD_REQUEST, 3, "Invalid parameter date");
            }
            Calendar c = GregorianCalendar.getInstance();
            c.setTime(parsedDate);
            List<FlightInstance> lsFi = fi.getFlightInstance(from, null, c, numTickets);
            if (lsFi != null && lsFi.size() > 0) {
                return mapResponse(lsFi, numTickets);
            }
            throw new FlightException(Response.Status.NOT_FOUND, 1, "No Flights");
        } else {
            logger.info("Inside searchFlightBetweenDestination invalid parameters");
            throw new FlightException(Response.Status.BAD_REQUEST, 3, "Invalid parameters");
        }
    }

    @POST
    @Path("/flightreservation")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ReservationResponse reserveFlight(ReservationRequest request) throws FlightException {
        logger.info("Inside reserveFlight");
        if (request == null) {
            throw new FlightException(Response.Status.BAD_REQUEST, 3, "Invalid parameters");
        }

        if (request.getFlightID() == null) {
            throw new FlightException(Response.Status.BAD_REQUEST, 3, "Flight ID is required");
        }

        if (request.getReserveeName() == null) {
            throw new FlightException(Response.Status.BAD_REQUEST, 3, "Reservee Name is required");
        }

        if (request.getNumberOfSeats() < 0) {
            throw new FlightException(Response.Status.BAD_REQUEST, 3, "Invalid number of seats");
        }

        if (request.getReserveEmail() == null) {
            throw new FlightException(Response.Status.BAD_REQUEST, 3, "Reservee Email is required");
        }

        if (request.getReservePhone() == null) {
            throw new FlightException(Response.Status.BAD_REQUEST, 3, "Reservee Phone is required");
        }

        if (!Util.validateEmail(request.getReserveEmail())) {
            throw new FlightException(Response.Status.BAD_REQUEST, 3, "Reservee Email is not valid");
        }

        if (request.getPassengers() == null || request.getPassengers().size() == 0) {
            throw new FlightException(Response.Status.BAD_REQUEST, 3, "Passengers are required");
        }

        //All input are valid let's check the availabilty of flight
        FlightDTO flightDTO = fi.getFlightById(request.getFlightID());
        logger.info(flightDTO);
        if (flightDTO == null || flightDTO.getFlights() == null || flightDTO.getNumberOfSeats() < request.getNumberOfSeats()) {
            throw new FlightException(Response.Status.BAD_REQUEST, 2, "Not enough seats are available in this flight");
        } else {
            FlightInstance fInstance = flightDTO.getFlights().get(0);
            fInstance.setAvailableSeats(fInstance.getAvailableSeats()-request.getNumberOfSeats());
            Reservation reservation = new Reservation();
            reservation.setPrice(fInstance.getPrice()*request.getNumberOfSeats());
            reservation.setInstance(fInstance);
            //Get the user
            User user = fi.getUserByEmail(request.getReserveEmail());
            reservation.setUser(user);
            for(Passenger p:request.getPassengers()){
                p.setReservation(reservation);
            }
            reservation.setPassengers(request.getPassengers());
            
            fi.reserveFlight(reservation);
            
            ReservationResponse response = new ReservationResponse();
            Flight flight=mapFlightInstanceToFlight(fInstance);
            response.setDate(flight.getDate());
            response.setDestination(flight.getDestination());
            response.setOrigin(flight.getOrigin());
            response.setFlightID(flight.getFlightId());
            response.setFlightTime(flight.getTravelTime());
            response.setNumberOfSeats(request.getNumberOfSeats());
            response.setPassengers(request.getPassengers());
            response.setReserveeName(request.getReserveeName());
            return response;
        }
    }
    
    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public User login(User user) throws FlightException {
        if(user==null){
            throw new FlightException(Response.Status.BAD_REQUEST, 3, "Email or password is required");
        }else{
            User userDB=fi.getUserByEmail(user.getEmail());
            if(userDB!=null && userDB.getPassword().equals(user.getPassword())){
                return user;
            }else{
                throw new FlightException(Response.Status.NOT_FOUND, 1, "Invalid user or password");
            }
        }
    }
    
    @POST
    @Path("/myreservations")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Reservation> getReservations(User user) throws FlightException{
        if(user==null){
            throw new FlightException(Response.Status.BAD_REQUEST, 3, "Email or password is required");
        }else{
            User userDB=fi.getUserByEmail(user.getEmail());
            if(userDB!=null){
                
                if(userDB.isIsAdmin()){
                    System.out.println("--------------------"+userDB);
                    return fi.getAllReservations();
                }else{
                    Set<Reservation> reservations=userDB.getReservations();
                    if(reservations!=null){
                        return new ArrayList<Reservation>(reservations);
                    }else{
                        return null;
                    }
                }
            }else{
                throw new FlightException(Response.Status.NOT_FOUND, 1, "Invalid user");
            }
        }
    }

}
