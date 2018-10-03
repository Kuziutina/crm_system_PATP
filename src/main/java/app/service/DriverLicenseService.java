package app.service;

import app.form.DriverLicenseForm;
import app.model.DriverLicense;
import app.model.Employee;
import app.repository.DriverLicenseRepository;
import app.service.interfaces.DriverLicenseServiceInt;
import app.service.interfaces.EmployeeServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverLicenseService implements DriverLicenseServiceInt {
    @Autowired
    private DriverLicenseRepository driverLicenseRepository;
    @Autowired
    private EmployeeServiceInt employeeService;
    @Override
    public void addNewDriverLicense(DriverLicense driverLicense) {
        driverLicenseRepository.save(driverLicense);
    }

    @Override
    public void addNewDriverLicense(DriverLicenseForm driverLicenseForm) {
        Employee owner = employeeService.find(driverLicenseForm.getLastName(), driverLicenseForm.getName(), driverLicenseForm.getFatherName());
        DriverLicense driverLicense = DriverLicense.builder()
                                        .info(driverLicenseForm.getInfo())
                                        .category(driverLicenseForm.getCategory())
                                        .dateApplication(driverLicenseForm.getDateApplication())
                                        .employee(owner)
                                        .build();
        addNewDriverLicense(driverLicense);
        owner.setDriverLicense(driverLicense);
        employeeService.update(owner.getId(), owner);
    }

    @Override
    public List<DriverLicense> getAllDriverLicenses() {
        return driverLicenseRepository.findAll();
    }

    @Override
    public DriverLicense find(long id) {
        return driverLicenseRepository.getOne(id);
    }

    @Override
    public void delete(long id) {
        driverLicenseRepository.deleteById(id);
    }

    @Override
    public void update(long id, DriverLicense driverLicense) {
        driverLicenseRepository.update(id, driverLicense);
    }

    @Override
    public void update(long id, DriverLicenseForm driverLicenseForm) {
        driverLicenseRepository.update(id, createDriverLicenseFromDriverLicenseForm(driverLicenseForm));
    }

    public DriverLicense createDriverLicenseFromDriverLicenseForm(DriverLicenseForm driverLicenseForm) {
        Employee owner = employeeService.find(driverLicenseForm.getLastName(), driverLicenseForm.getName(), driverLicenseForm.getFatherName());
        DriverLicense driverLicense = DriverLicense.builder()
                .info(driverLicenseForm.getInfo())
                .category(driverLicenseForm.getCategory())
                .dateApplication(driverLicenseForm.getDateApplication())
                .employee(owner)
                .build();
        return driverLicense;
    }


}
