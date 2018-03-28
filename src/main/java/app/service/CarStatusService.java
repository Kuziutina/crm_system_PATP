package app.service;

import app.model.Car;
import app.model.CarStatus;
import app.repository.CarStatusRepository;
import app.service.interfaces.CarServiceInt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarStatusService{
    private CarStatusRepository carStatusRepository;

    public CarStatusService(CarStatusRepository carStatusRepository) {
        this.carStatusRepository = carStatusRepository;
    }

    public void addNewCar(CarStatus car) {
        carStatusRepository.save(car);
    }

    public List<CarStatus> getAllCarStatuses() {
        return carStatusRepository.findAll();
    }

    public CarStatus find(long id) {
        return carStatusRepository.getOne(id);
    }

    public void delete(long id) {
        CarStatus carStatus = find(id);
        carStatusRepository.delete(carStatus);
    }
}
