package app.dn;

import app.model.User;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    private long id;
    @NotNull
    @NotEmpty
    private String login;
    private String role;

    private UserDTO(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.role = user.getRole();
    }

    public static UserDTO getByUser(User user) {
        return new UserDTO(user);
    }

}