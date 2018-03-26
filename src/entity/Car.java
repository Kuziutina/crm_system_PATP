package entity;

import javax.persistence.*;

@Entity
@Table(name = "car")
public class Car {
    private int id;
    private int route_id;
    private int driver_id;
    private int conductor_id;
    private String car_number;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "car_id_seq")
    @SequenceGenerator(name = "car_id_seq", sequenceName = "car_id_seq", allocationSize = 1)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "route_id")
    public int getRoute_id() {
        return route_id;
    }

    public void setRoute_id(int route_id) {
        this.route_id = route_id;
    }

    @Basic
    @Column(name = "driver")
    public int getDriverId() {
        return driver_id;
    }

    public void setDriverId(int driver_id) {
        this.driver_id = driver_id;
    }

    @Basic
    @Column(name = "conductor")
    public int getConductorId() {
        return conductor_id;
    }

    public void setConductorId(int conductor_id) {
        this.conductor_id = conductor_id;
    }

    @Basic
    @Column(name = "car_number")
    public String getCar_number() {
        return car_number;
    }

    public void setCar_number(String car_number) {
        this.car_number = car_number;
    }

    private Route route;
    private Employee driver;
    private Employee conductor;

    @ManyToOne
    @JoinColumn(name = "route_id", referencedColumnName = "id")
    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    @ManyToOne
    @JoinColumn(name = "driver", referencedColumnName = "id")
    public Employee getDriver() {
        return driver;
    }

    public void setDriver(Employee driver) {
        this.driver = driver;
    }

    @ManyToOne
    @JoinColumn(name = "conductor", referencedColumnName = "id")
    public Employee getConductor() {
        return conductor;
    }

    public void setConductor(Employee conductor) {
        this.conductor = conductor;
    }
}
