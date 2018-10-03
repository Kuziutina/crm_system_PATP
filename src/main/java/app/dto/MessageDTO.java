package app.dto;

import app.model.Message;
import app.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {
    private String sender_name;
    private String body;
    private String date;

    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public MessageDTO(Message message) {
        this.sender_name = message.getSender().getLogin();
        this.body = message.getBody();
        this.date = dateFormat.format(message.getDate());
    }
}
