package app.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class CarStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "carStatus")
    private List<Car> cars;
}
