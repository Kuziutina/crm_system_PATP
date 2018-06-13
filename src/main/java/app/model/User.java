package app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "\"users\"")

public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @NotFound(action = NotFoundAction.IGNORE)
    private Long id;

    private String login;

    private String password;

    private String role;

    @OneToOne(mappedBy = "user")
    private Employee employee;

    @OneToOne(mappedBy = "user")
    private UserData userData;

    @OneToMany(mappedBy = "user")
    private List<Ticket> tickets;

    @OneToOne(mappedBy = "user")
    private UserImage userImage;

    @OneToMany(mappedBy = "recipient")
    private List<Message> messagesGet;

    @JsonBackReference
    @OneToMany(mappedBy = "sender")
    private List<Message> messagesSend;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, login, password, role);
    }
}
