package app.service.interfaces;

import app.model.CarStatus;

import java.util.List;

public interface CarStatusServiceInt {
    void addCarStatus(CarStatus carStatus);
    List<CarStatus> getAllCarStatuses();
    CarStatus find(long id);
    CarStatus find(String name);
    void delete(long id);
}
