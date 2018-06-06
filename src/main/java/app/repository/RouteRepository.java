package app.repository;

import app.model.Route;
import app.model.Station;
import app.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RouteRepository extends JpaRepository<Route, Long> {
    Route findOneByName(String name);
    Route findOneByNameAndType(String name, Type type);
    List<Route> findAllByStationsContaining(Station station);
}
