package app.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class DriverLicense {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String category;

    private Date dateApplication;

    @ManyToMany(mappedBy = "driverLicenses")
    private List<Employee> employees;
}
