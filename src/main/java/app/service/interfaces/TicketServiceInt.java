package app.service.interfaces;

import app.form.TicketFeedbackForm;
import app.model.Ticket;

import java.util.List;

public interface TicketServiceInt {
    void addNewTicket(Ticket ticket);
    List<Ticket> getAllTickets();
    List<Ticket> getAllTicketsByStatus(String status);
    Ticket find(long id);
    void delete(long id);
    void addNewTicket(TicketFeedbackForm ticketFeedbackForm);
}
