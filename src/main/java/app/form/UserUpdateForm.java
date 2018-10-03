package app.form;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserUpdateForm {
    private String lastName;
    private String name;
    private String fatherName;
    private String email;
}
