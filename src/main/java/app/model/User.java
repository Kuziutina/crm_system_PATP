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
@Table(name = "\"users\"")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String login;

    private String password;

    private String role;

    @OneToOne(mappedBy = "user")
    private Employee employee;

    @OneToMany(mappedBy = "user")
    private List<Ticket> tickets;
}
