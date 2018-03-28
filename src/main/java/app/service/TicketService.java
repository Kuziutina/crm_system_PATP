package app.service;

import app.model.Ticket;
import app.repository.TicketRepository;
import app.service.interfaces.TicketServiceInt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService implements TicketServiceInt {
    private TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public void addNewTicket(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
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
