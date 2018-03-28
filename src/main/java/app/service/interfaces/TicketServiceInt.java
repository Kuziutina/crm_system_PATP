package app.service.interfaces;

import app.model.Ticket;

import java.util.List;

public interface TicketServiceInt {
    void addNewTicket(Ticket ticket);
    List<Ticket> getAllTickets();
    Ticket find(long id);
    void delete(long id);
}
