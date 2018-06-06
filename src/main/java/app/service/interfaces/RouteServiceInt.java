package app.service.interfaces;

import app.dto.RouteDTO;
import app.form.EmployeeForm;
import app.form.RouteForm;
import app.model.Route;
import app.model.Station;

import java.util.List;

public interface RouteServiceInt {
    void addNewRoute(Route route);
    void addNewRoute(RouteForm routeForm);
    List<Route> getAllRoutes();
    Route find(long id);
    Route find(String name);
    Route find(String name, String type);
    List<RouteDTO> findByStationFilter(String station);
    void addStationOnRoute(long route_id, Station station);
    void deleteStationFromRoute(long route_id, long station_id);
    void deleteStationFromRoute(long route_id, Station station);
    void update(long id, RouteForm routeForm);
    void update(Route route);
    void delete(long id);
}
