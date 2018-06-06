package app.service.interfaces;

import app.form.CarForm;
import app.model.Car;

import java.util.List;

public interface CarServiceInt {
    void addNewCar(Car car);
    void addNewCar(CarForm carForm);
    List<Car> getAllCars();
    Car find(long id);
    Car find(String name);
    void delete(long id);
    void update(long id, Car car);
    void update(long id, CarForm carForm);
}
