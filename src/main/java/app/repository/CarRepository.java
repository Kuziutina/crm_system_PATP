package app.repository;

import app.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CarRepository extends JpaRepository<Car, Long> {
    Car getOneByNumber(String number);


    @Modifying
    @Transactional
    @Query("UPDATE Car SET number = :#{#changedCar.number}, carStatus = :#{#changedCar.carStatus}, route = :#{#changedCar.route}, " +
            " lastCheckUp = :#{#changedCar.lastCheckUp} WHERE id = :id")
    void update(@Param("id") long id, @Param("changedCar") Car changedCar);

}
