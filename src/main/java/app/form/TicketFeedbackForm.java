package app.form;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketFeedbackForm implements Serializable{
    private Long id;
    private String name;
    private String email;
    private String theme;
    private String body;
    private long userId;

//    private Date date;

}
