package app.repository;

import app.model.Ticket;
import app.model.TicketStatus;
import app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findAllByTicketStatus(TicketStatus ticketStatus);

    List<Ticket> findAllByTicketStatusAndUser(TicketStatus ticketStatus, User user);
    List<Ticket> findAllByTicketStatusNotAndUser(TicketStatus ticketStatus, User user);
}
