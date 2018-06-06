package app.service.interfaces;

import app.form.StationForm;
import app.model.Station;

import java.util.List;

public interface StationServiceInt {
    void addNewStation(Station station);
    void addNewStation(StationForm stationForm);
    List<Station> getAllStations();
    Station find(long id);
    Station find(String name);
    List<Station> findByPartName(String name);
    void update(long id, StationForm stationForm);
    void delete(long id);
}
