package service;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * This is the main application class used to start the REST API application.
 */
public class FlightApplication extends Application {

    private Set<Object> _singletons = new HashSet<Object>();

    public FlightApplication() {
        _singletons.add(new AirlineEngineService());
    }

    @Override
    public Set<Object> getSingletons() {
        return _singletons;
    }

}
