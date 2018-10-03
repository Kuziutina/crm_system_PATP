package app.service;

import app.model.Car;
import app.model.CarStatus;
import app.repository.CarStatusRepository;
import app.service.interfaces.CarServiceInt;
import app.service.interfaces.CarStatusServiceInt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarStatusService implements CarStatusServiceInt{
    private CarStatusRepository carStatusRepository;

    public CarStatusService(CarStatusRepository carStatusRepository) {
        this.carStatusRepository = carStatusRepository;
    }

    @Override
    public void addCarStatus(CarStatus car) {
        carStatusRepository.save(car);
    }

    @Override
    public List<CarStatus> getAllCarStatuses() {
        return carStatusRepository.findAll();
    }

    public CarStatus find(long id) {
        return carStatusRepository.getOne(id);
    }

    @Override
    public CarStatus find(String name) {
        return carStatusRepository.getOneByName(name);
    }

    public void delete(long id) {
        CarStatus carStatus = find(id);
        carStatusRepository.delete(carStatus);
    }
}
