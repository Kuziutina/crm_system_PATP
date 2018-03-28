package app.service.interfaces;

import app.model.Car;

import java.util.List;

public interface CarServiceInt {
    void addNewCar(Car car);
    List<Car> getAllCars();
    Car find(long id);
    void delete(long id);
}
