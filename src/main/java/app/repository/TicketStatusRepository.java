package app.repository;

import app.model.Ticket;
import app.model.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketStatusRepository extends JpaRepository<TicketStatus, Long> {
    public TicketStatus getTicketStatusByName(String status);
}
