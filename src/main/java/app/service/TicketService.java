package app.service;

import app.form.TicketFeedbackForm;
import app.model.Ticket;
import app.model.TicketStatus;
import app.repository.TicketRepository;
import app.repository.TicketStatusRepository;
import app.service.interfaces.TicketServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService implements TicketServiceInt {
    private TicketRepository ticketRepository;
    @Autowired
    private TicketStatusRepository ticketStatusRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public void addNewTicket(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    @Override
    public void addNewTicket(TicketFeedbackForm ticketFeedbackForm) {
        Ticket ticket = Ticket.builder()
                            .name(ticketFeedbackForm.getName())
                            .body(ticketFeedbackForm.getBody())
                            .ticketStatus(ticketStatusRepository.getTicketStatusByName("обратная связь"))
//                            .dateApplication(ticketFeedbackForm.getDate())
                            .email(ticketFeedbackForm.getEmail())
                            .theme(ticketFeedbackForm.getTheme())
                            .build();
    }

    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public List<Ticket> getAllTicketsByStatus(String status) {
        TicketStatus ticketStatus = ticketStatusRepository.getTicketStatusByName(status);
        return ticketRepository.findAllByTicketStatus(ticketStatus);
    }

    @Override
    public Ticket find(long id) {
        return ticketRepository.getOne(id);
    }

    @Override
    public void delete(long id) {
        Ticket ticket = find(id);
        ticketRepository.delete(ticket);

    }
}
