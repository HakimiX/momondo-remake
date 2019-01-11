/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import com.flightapi.dto.Airport;
import com.flightapi.dto.FlightDTO;
import com.flightapi.dto.FlightInstance;
import com.flightapi.dto.Reservation;
import com.flightapi.dto.User;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * This is the JPA handler for database
 *
 * @author
 */
public class FacadeController implements FacadeInterface {

    private EntityManagerFactory EMF = Persistence.createEntityManagerFactory("flightengine", null);
    private EntityManager EM = EMF.createEntityManager();

    private static FacadeController instance = null;

    public FacadeController() {

    }

    public Airport getAirport(String airport) {
        return EM.find(Airport.class, airport);
    }

    public List<FlightInstance> getFlightInstance(String from, String to, Calendar date, int numberOfSeats) {
        CriteriaBuilder cb = EM.getCriteriaBuilder();
        CriteriaQuery<FlightInstance> q = cb.createQuery(FlightInstance.class);
        Root<FlightInstance> c = q.from(FlightInstance.class);
        q.select(c);
        List<Predicate> predicateList = new ArrayList<Predicate>();
        Predicate predicate;
        predicate = cb.equal(c.get("departureDate"), date);
        predicateList.add(predicate);

        Expression<Collection<Airport>> fliesFrom = c.get("fliesFrom");
        predicate = cb.isMember(getAirport(from), fliesFrom);
        predicateList.add(predicate);

        Expression<Integer> noOfSeats = c.get("availableSeats");
        predicate = cb.ge(noOfSeats, numberOfSeats);
        predicateList.add(predicate);

        if (to != null) {
            predicate = cb.equal(c.get("destination"), getAirport(to));
            predicateList.add(predicate);
        }

        Predicate[] predicates = new Predicate[predicateList.size()];
        predicateList.toArray(predicates);
        q.where(predicates);
        TypedQuery<FlightInstance> query = EM.createQuery(q);
        List<FlightInstance> ls = query.getResultList();
        return ls;
    }

    @Override
    public FlightDTO getFlightById(String flightId) {
        return EM.find(FlightDTO.class, flightId);
    }

    @Override
    public void reserveFlight(Reservation reservation) {
        EM.getTransaction().begin();
        EM.persist(reservation);
        EM.getTransaction().commit();
    }

    @Override
    public User getUserByEmail(String email) {
        return EM.find(User.class, email);
    }

    @Override
    public List<Reservation> getAllReservations() {
        Query query = EM.createQuery("select l from Reservation l order by l.id");
        return query.getResultList();
    }
    
}
