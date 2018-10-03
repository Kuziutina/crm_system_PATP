package app.form;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketChangeForm {
    private long id;
    private String status;
}
