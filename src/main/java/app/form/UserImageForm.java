package app.form;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UserImageForm {
    private Long userId;
    private MultipartFile imageUser;

    @Override
    public String toString() {
        return "UserImageForm{" +
                "userId=" + userId +
                ", imageUser=" + imageUser +
                '}';
    }
}
