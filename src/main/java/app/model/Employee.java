package app.model;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
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

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "position_id", referencedColumnName = "id")
    private Position position;

    @ManyToOne
    @JoinColumn(name = "route_id", referencedColumnName = "id")
    private Route route;

    @OneToOne(mappedBy = "employee", fetch = FetchType.LAZY)
    private DriverLicense driverLicense;
}
