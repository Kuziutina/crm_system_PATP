package app.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String number;

//    @Column(name = "route_id")
//    private Integer route_id;
//    @Column(name = "driver_id")
//    private Integer driver_id;
//    @Column(name = "conductor_id")
//    private Integer conductor_id;
//    @Column(name = "status_id")
//    private Integer status_id;

    private Date lastCheckup;

    @ManyToOne
    @JoinColumn(name = "driver_id", referencedColumnName = "id")
    private Employee driver;

    @ManyToOne
    @JoinColumn(name = "conductor_id", referencedColumnName = "id")
    private Employee conductor;

    @ManyToOne
    @JoinColumn(name = "route_id", referencedColumnName = "id")
    private Route route;

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private CarStatus carStatus;
}
