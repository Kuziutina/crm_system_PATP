package app.service.interfaces;

import app.form.DriverLicenseForm;
import app.model.DriverLicense;

import java.util.List;

public interface DriverLicenseServiceInt {
    void addNewDriverLicense(DriverLicense driverLicense);
    void addNewDriverLicense(DriverLicenseForm driverLicenseForm);
    List<DriverLicense> getAllDriverLicenses();
    DriverLicense find(long id);
    void delete(long id);
    void update(long id, DriverLicense driverLicense);
    void update(long id, DriverLicenseForm driverLicenseForm);
}
