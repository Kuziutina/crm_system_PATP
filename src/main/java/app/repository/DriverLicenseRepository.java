package app.repository;

import app.model.DriverLicense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface DriverLicenseRepository extends JpaRepository<DriverLicense, Long> {


    @Modifying
    @Transactional
    @Query("UPDATE DriverLicense SET employee = :#{#changedDriverLicense.employee}, category = :#{#changedDriverLicense.category}, info = :#{#changedDriverLicense.info}, " +
            " dateApplication = :#{#changedDriverLicense.dateApplication} WHERE id = :id")
    void update(@Param("id") long id, @Param("changedDriverLicense") DriverLicense changedDriverLicense);
}
