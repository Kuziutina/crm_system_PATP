package app.service;

import app.dto.RouteDTO;
import app.dto.StationDTO;
import app.form.RouteForm;
import app.model.Route;
import app.model.Station;
import app.repository.RouteRepository;
import app.repository.TypeRepository;
import app.service.interfaces.RouteServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RouteService implements RouteServiceInt {

    @Autowired
    RouteRepository routeRepository;
    @Autowired
    TypeRepository typeRepository;
    @Autowired
    StationService stationService;
    @Override
    public void addNewRoute(Route route) {
        routeRepository.save(route);
    }

    @Override
    public void addNewRoute(RouteForm routeForm) {
        Route route = Route.builder()
                        .name(routeForm.getName())
                        .type(typeRepository.getOneByName(routeForm.getType()))
                        .build();
        System.out.println("i get type: " + route.getType());
        addNewRoute(route);
    }

    public void addStationOnRoute(long route_id, Station station) {
        Route route = find(route_id);
        route.getStations().add(station);
        update(route);
    }

    @Override
    public void deleteStationFromRoute(long route_id, long station_id) {
        Station station = stationService.find(station_id);
        deleteStationFromRoute(route_id, station);
    }

    @Override
    public void deleteStationFromRoute(long route_id, Station station) {
        Route route = find(route_id);
        route.getStations().remove(station);
        update(route);
    }

    @Override
    public void update(long id, RouteForm routeForm) {
        Route route = find(id);
        route.setName(routeForm.getName());
        route.setType(typeRepository.getOneByName(routeForm.getType()));
        update(route);
    }

    @Override
    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    @Override
    public Route find(long id) {
        return routeRepository.getOne(id);
    }

    @Override
    public Route find(String name) {
        return routeRepository.findOneByName(name);
    }

    @Override
    public Route find(String name, String type) {
        return routeRepository.findOneByNameAndType(name, typeRepository.getOneByName(type));
    }

    @Override
    public List<RouteDTO> findByStationFilter(String stationName) {
        if(stationName.equals("all-stations")) {
            stationName = "";
        }
        List<Station> stations = stationService.findByPartName(stationName);
        Set<Route> simple_routes = new HashSet<>();
        List<RouteDTO> routes = new ArrayList<>();
        for (Station station: stations) {
            simple_routes.addAll(routeRepository.findAllByStationsContaining(station));
        }
        System.out.println("NEW");
        for (Route route: simple_routes) {
            System.out.println(route.getName());
            routes.add(createRouteDTO(route));
        }
        return routes;
    }

    public void update(Route route) {
        routeRepository.save(route);
    }

    @Override
    public void delete(long id) {
        Route route = find(id);
        route.setStations(new ArrayList<>());
        update(route);
        routeRepository.deleteById(id);
    }

    private RouteDTO createRouteDTO(Route route) {
        List<StationDTO> stationDTOs = new ArrayList<>();
        for (Station station: route.getStations()) {
            stationDTOs.add(new StationDTO(station.getId(), station.getName()));
        }
        RouteDTO routeDTO = RouteDTO.builder().id(route.getId())
                                .name(route.getName())
                                .type(route.getType().getName())
                                .stations(stationDTOs)
                                .build();
        return routeDTO;
    }
}
