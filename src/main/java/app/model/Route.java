package app.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    private Type type;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "route_station",
            joinColumns = {@JoinColumn(name = "route_id")},
            inverseJoinColumns = {@JoinColumn(name = "station_id")}
    )
    private List<Station> stations;

    @OneToMany(mappedBy = "route")
    private List<Employee> employees;

    @OneToMany(mappedBy = "route")
    private List<Car> cars;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return Objects.equals(id, route.id) &&
                Objects.equals(name, route.name) &&
                Objects.equals(type.getName(), route.type.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type.getName());
    }
}
