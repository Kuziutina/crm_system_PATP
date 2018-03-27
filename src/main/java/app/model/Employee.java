package app.model;


import javax.persistence.*;
import java.util.List;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "last_name")
    private String lastName;

    private String name;

    @Column(name = "father_name")
    private String fatherName;

    private Integer experience;

    private Integer salary;

//    @Column(name = "user_id")
//    private Integer user_id;

//    @Column(name = "position_id")
//    private Integer position_id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "position_id", referencedColumnName = "id")
    private Position position;

    @OneToMany(mappedBy = "driver")
    private List<Car> carDriver;

    @OneToMany(mappedBy = "conductor")
    private List<Car> carConductor;

    @ManyToMany()
    @JoinTable(
            name = "drivers",
            joinColumns = {@JoinColumn(name = "employee_id")},
            inverseJoinColumns = {@JoinColumn(name = "driver_license_id")}
    )
    private List<DriverLicense> driverLicenses;
}
