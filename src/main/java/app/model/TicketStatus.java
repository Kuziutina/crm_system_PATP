package app.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class TicketStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "ticketStatus")
    private List<Ticket> tickets;
}
