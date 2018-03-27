package app.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "\"user\"")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String login;

    private String password;

    @OneToMany(mappedBy = "user")
    private List<Employee> employee;

    @OneToMany(mappedBy = "user")
    private List<Ticket> tickets;
}
