package app;

import app.form.TicketForm;
import app.model.Ticket;
import app.model.TicketStatus;
import app.service.TicketService;
import app.service.interfaces.TicketServiceInt;
import com.google.inject.internal.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TicketServiceTest {
    private TicketServiceInt ticketService;

    @Before
    public void init() {
        ticketService = mock(TicketService.class);
    }

    @Test
    public void getAllTicketTest() {
        List list = mock(List.class);
        when(ticketService.getAllTickets()).thenReturn(list);
        Assert.assertEquals(list, ticketService.getAllTickets());
    }

    @Test
    public void whenGetAllTickets_thenReturnListOfTickets() {
        Ticket ticket = Ticket.builder().theme("theme").email("email").dateApplication(mock(Date.class)).ticketStatus(mock(TicketStatus.class)).body("body").build();
        Ticket ticket2 = Ticket.builder().theme("theme2").email("email2").dateApplication(mock(Date.class)).ticketStatus(mock(TicketStatus.class)).body("body2").build();
        Mockito.when(ticketService.getAllTickets()).thenReturn(Lists.newArrayList(ticket, ticket2));

        Assert.assertEquals(ticketService.getAllTickets().size(), 2);
    }

    @Test
    public void addTicket(){
        TicketForm ticketForm = TicketForm.builder().body("body").build();
        ticketService.addNewTicket(ticketForm);
        Mockito.verify(ticketService).addNewTicket(ticketForm);
    }
}


