package app.form;

import lombok.*;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketForm implements Serializable{
    private Long id;
    private String name;
    private String email;
    private String theme;
    private String body;
    private long userId;

    private Date date;
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public void setDate(String date) {
        try {
            this.date = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
