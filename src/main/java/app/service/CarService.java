package app.service;

import app.model.Car;
import app.repository.CarRepository;
import app.service.interfaces.CarServiceInt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService implements CarServiceInt{
    private CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public void addNewCar(Car car) {
        carRepository.save(car);
    }

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public Car find(long id) {
        return carRepository.getOne(id);
    }

    @Override
    public void delete(long id) {
        Car car = find(id);
        carRepository.delete(car);
    }
}
