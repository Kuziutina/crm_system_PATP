package app.service.interfaces;

import app.dn.FileInfo;
import app.form.TicketChangeForm;
import app.form.TicketFeedbackForm;
import app.form.TicketForm;
import app.model.Ticket;
import app.model.User;
import org.openid4java.util.HttpResponse;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface TicketServiceInt {
    void addNewTicket(Ticket ticket);
    List<Ticket> getAllTickets();
    List<Ticket> getAllTicketsByStatus(String status);
    List<Ticket> getAllTicketsByStatusAndUser(User user, String status);
    List<Ticket> getAllUsersTicket(User user);
    List<Ticket> getAllUsersFeedback(User user);
    Ticket find(long id);
    void delete(long id);
    Ticket addNewFeedback(TicketFeedbackForm ticketFeedbackForm);
    Ticket addNewTicket(TicketForm ticketForm);
    void update(TicketChangeForm ticketChangeForm);
    FileInfo generateTicketDoc(long id);
    void loadTicketDoc(long id, HttpServletResponse response);
}
