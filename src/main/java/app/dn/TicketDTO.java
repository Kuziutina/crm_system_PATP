package app.dn;

import app.model.Ticket;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketDTO {
    private Long id;
    private String name;
    private String email;
    private String theme;
    private String body;

    private Date date;

    public TicketDTO(Ticket ticket) {
        this.id = ticket.getId();
        this.name = ticket.getName();
        this.email = ticket.getEmail();
        this.theme = ticket.getTheme();
        this.body = ticket.getBody();
        this.date = ticket.getDateApplication();
    }

    public static TicketDTO getByTicket(Ticket ticket) {
        return new TicketDTO(ticket);
    }
}
