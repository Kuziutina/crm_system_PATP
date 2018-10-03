package app.service;

import app.form.StationForm;
import app.model.Station;
import app.repository.StationRepository;
import app.service.interfaces.StationServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationService implements StationServiceInt{
    @Autowired
    private StationRepository stationRepository;

    @Override
    public void addNewStation(Station station) {
        stationRepository.save(station);
    }

    @Override
    public void addNewStation(StationForm stationForm) {
        addNewStation(createStationFromStationForm(stationForm));
    }

    @Override
    public List<Station> getAllStations() {
        return stationRepository.findAll();
    }

    @Override
    public Station find(long id) {
        return stationRepository.getOne(id);
    }

    @Override
    public Station find(String name) {
        return stationRepository.findOneByName(name);
    }

    @Override
    public List<Station> findByPartName(String name) {
        return stationRepository.findAllByNameContaining(name);
    }

    @Override
    public void update(long id, StationForm stationForm) {
        Station station = find(id);
        station.setName(stationForm.getName());
        stationRepository.save(station);
    }

    @Override
    public void delete(long id) {
        stationRepository.deleteById(id);
    }

    private Station createStationFromStationForm(StationForm stationForm) {
        return Station.builder().name(stationForm.getName()).build();
    }
}
