package app.service;

import app.form.CarForm;
import app.model.Car;
import app.repository.CarRepository;
import app.service.interfaces.CarServiceInt;
import app.service.interfaces.RouteServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService implements CarServiceInt{
    private CarRepository carRepository;
    @Autowired
    private CarStatusService carStatusService;
    @Autowired
    private RouteServiceInt routeService;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public void addNewCar(Car car) {
        carRepository.save(car);
    }

    @Override
    public void addNewCar(CarForm carForm) {
        addNewCar(createCarFromCarForm(carForm));
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
    public Car find(String name) {
        return carRepository.getOneByNumber(name);
    }

    @Override
    public void delete(long id) {
        Car car = find(id);
        carRepository.delete(car);
    }

    @Override
    public void update(long id, Car car) {
        carRepository.update(id, car);
    }

    @Override
    public void update(long id, CarForm carForm) {
        update(id, createCarFromCarForm(carForm));
    }

    public Car createCarFromCarForm(CarForm carForm) {
        Car car = Car.builder()
                    .number(carForm.getNumber())
                    .lastCheckUp(carForm.getLastCheckUp())
                    .route(routeService.find(carForm.getRouteName()))
                    .carStatus(carStatusService.find(carForm.getCarStatusName()))
                    .build();
        return car;
    }
}
