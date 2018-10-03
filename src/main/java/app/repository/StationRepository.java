package app.repository;

import app.model.Station;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StationRepository extends JpaRepository<Station, Long> {
    Station findOneByName(String name);
    List<Station> findAllByNameContaining(String name);
}
