package app.form;

import lombok.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddMessageForm {
    private Long recipient_id;
    private Long sender_id;
    private String body;
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

