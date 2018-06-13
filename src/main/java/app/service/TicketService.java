package app.service;

import app.dn.FileInfo;
import app.form.TicketChangeForm;
import app.form.TicketFeedbackForm;
import app.form.TicketForm;
import app.model.Ticket;
import app.model.TicketStatus;
import app.model.User;
import app.repository.TicketRepository;
import app.repository.TicketStatusRepository;
import app.service.interfaces.TicketServiceInt;
import app.service.interfaces.UserServiceInt;
import app.util.TicketDocsGenerator;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.openid4java.util.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

@Service
public class TicketService implements TicketServiceInt {
    private TicketRepository ticketRepository;
    @Autowired
    private TicketStatusRepository ticketStatusRepository;
    @Autowired
    private TicketDocsGenerator ticketDocsGenerator;
    @Autowired
    private UserServiceInt userService;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public void addNewTicket(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    @Override
    public Ticket addNewFeedback(TicketFeedbackForm ticketFeedbackForm) {
        User user = userService.find(ticketFeedbackForm.getUserId());
        if (ticketFeedbackForm.getUserId() == 0) user= null;
        Ticket ticket = Ticket.builder()
                            .name(ticketFeedbackForm.getName())
                            .body(ticketFeedbackForm.getBody())
                            .ticketStatus(ticketStatusRepository.getTicketStatusByName("Отзыв"))
                            .dateApplication(new Date())
                            .email(ticketFeedbackForm.getEmail())
                            .theme(ticketFeedbackForm.getTheme())
                            .user(user)
                            .build();
        addNewTicket(ticket);
        return ticket;
    }

    @Override
    public Ticket addNewTicket(TicketForm ticketForm) {
        User user = userService.find(ticketForm.getUserId());
        if (ticketForm.getUserId() == 0) user = null;
        Ticket ticket = Ticket.builder()
                .name(ticketForm.getName())
                .body(ticketForm.getBody())
                .ticketStatus(ticketStatusRepository.getTicketStatusByName("Новая"))
                .dateApplication(ticketForm.getDate())
                .email(ticketForm.getEmail())
                .theme(ticketForm.getTheme())
                .user(user)
                .build();
        addNewTicket(ticket);
        return ticket;
    }

    @Override
    public void update(TicketChangeForm ticketChangeForm) {
        TicketStatus ticketStatus = ticketStatusRepository.getTicketStatusByName(ticketChangeForm.getStatus());
        Ticket ticket = find(ticketChangeForm.getId());
        ticket.setTicketStatus(ticketStatus);
        ticketRepository.save(ticket);
    }

    @Override
    public FileInfo generateTicketDoc(long id) {
        Ticket ticket = find(id);
        return ticketDocsGenerator.generateTicketDoc(ticket);

    }

    @Override
    public void loadTicketDoc(long id, HttpServletResponse response) {
        FileInfo fileInfo = generateTicketDoc(id);
        writeFileToResponse(fileInfo, response);
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
    public List<Ticket> getAllTicketsByStatusAndUser(User user, String status) {
        TicketStatus ticketStatus = ticketStatusRepository.getTicketStatusByName(status);

        return ticketRepository.findAllByTicketStatusAndUser(ticketStatus, user);
    }

    @Override
    public List<Ticket> getAllUsersTicket(User user) {
        TicketStatus ticketStatus = ticketStatusRepository.getTicketStatusByName("Отзыв");

        return ticketRepository.findAllByTicketStatusNotAndUser(ticketStatus, user);
    }

    @Override
    public List<Ticket> getAllUsersFeedback(User user) {
        TicketStatus ticketStatus = ticketStatusRepository.getTicketStatusByName("Отзыв");

        return ticketRepository.findAllByTicketStatusAndUser(ticketStatus, user);
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

    @SneakyThrows
    private void writeFileToResponse(FileInfo fileInfo, HttpServletResponse response) {
        response.setContentType(fileInfo.getType());
        InputStream inputStream = new FileInputStream(new File(fileInfo.getPath()));
        IOUtils.copy(inputStream, response.getOutputStream());
        response.flushBuffer();
    }
}
