package app.repository;

import app.model.Ticket;
import app.model.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findAllByTicketStatus(TicketStatus ticketStatus);
}
